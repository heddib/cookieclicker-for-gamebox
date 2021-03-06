package me.nikl.cookieclicker.upgrades;

import me.nikl.cookieclicker.CCGame;
import me.nikl.cookieclicker.CCGameManager;
import me.nikl.cookieclicker.CCLanguage;
import me.nikl.cookieclicker.CookieClicker;
import me.nikl.cookieclicker.buildings.Building;
import me.nikl.cookieclicker.buildings.Buildings;
import me.nikl.gamebox.GameBox;
import me.nikl.gamebox.GameBoxSettings;
import me.nikl.gamebox.utility.NumberUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author Niklas Eicker
 */
public abstract class Upgrade {
    protected int id;
    protected Map<Buildings, Integer> productionsRequirements;
    protected double cost;
    protected ItemStack icon;
    protected String gain = "NULL";
    protected List<String> lore;
    protected String name;
    protected CookieClicker game;
    private Map<String, Double> cookiesRequirements;
    private CCLanguage lang;

    public Upgrade(CookieClicker game, int id) {
        this.id = id;
        productionsRequirements = new HashMap<>();
        cookiesRequirements = new HashMap<>();
        icon = new MaterialData(Material.BARRIER).toItemStack();
        icon.setAmount(1);
        this.cost = 1;
        this.game = game;

        this.lang = (CCLanguage) game.getGameLang();
    }

    protected void setTotalCookieReq(double count) {
        cookiesRequirements.put("total", count);
    }

    protected void setClickCookieReq(double count) {
        cookiesRequirements.put("click", count);
    }

    public boolean isUnlocked(CCGame game) {
        for (String key : cookiesRequirements.keySet()) {
            switch (key) {
                case "total":
                    if (game.getTotalCookiesProduced() < cookiesRequirements.get(key)) {
                        return false;
                    }
                    break;
                case "click":
                    if (game.getClickCookiesProduced() < cookiesRequirements.get(key)) {
                        return false;
                    }
                    break;
                default:
                    Bukkit.getLogger().log(Level.SEVERE, "unknown cookie requirement");
                    return false;
            }
        }

        for (Buildings buildings : productionsRequirements.keySet()) {
            if (game.getBuilding(buildings).getCount(game.getGameUuid()) < productionsRequirements.get(buildings)) {
                return false;
            }
        }

        return true;
    }

    protected void loadLanguage(UpgradeType upgradeType, Buildings... buildings) {
        name = lang.upgradeName.get(id);

        // for the standard upgrade type the building icon is used
        if (upgradeType == UpgradeType.CLASSIC && buildings != null && buildings.length == 1) {
            icon = game.getBuildings().get(buildings[0]).getIcon();
        }

        lore = new ArrayList<>();
        for (String line : lang.upgradeLore.get(upgradeType)) {
            line = line.replace("%cost%", NumberUtility.convertHugeNumber(cost)
                    .replace("%cost_long%", NumberUtility.convertHugeNumber(cost, false)));

            if (buildings != null && buildings.length > 0) {
                line = line.replace("%building%", lang.buildingName.get(buildings[0]).toLowerCase())
                        .replace("%Building%", lang.buildingName.get(buildings[0]));
            }

            switch (upgradeType) {
                case CLASSIC:
                case CLASSIC_MOUSE:
                    break;

                case GAIN_MOUSE_AND_OTHER:
                case GAIN_MOUSE_PER_CPS:
                    line = line.replace("%gain%", gain);
                    break;

                default:
                    GameBox.debug("not handled upgradeType is trying to load language");
                    break;
            }

            lore.add(line);
        }
        lore.addAll(lang.upgradeDescriptionLore.get(id));


        ItemMeta meta = icon.getItemMeta();
        if (!GameBoxSettings.version1_8) meta.addItemFlags(ItemFlag.values());
        meta.setDisplayName(lang.GAME_UPGRADE_NAME.replace("%name%", name));
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    public abstract void onActivation(CCGame game);

    public double getCost() {
        return this.cost;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public int getId() {
        return id;
    }
}
