package me.nikl.cookieclicker.upgrades.curser;

import me.nikl.cookieclicker.CCGame;
import me.nikl.cookieclicker.CookieClicker;
import me.nikl.cookieclicker.buildings.Buildings;
import me.nikl.cookieclicker.upgrades.Upgrade;
import me.nikl.cookieclicker.upgrades.UpgradeType;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * @author Niklas Eicker
 */
public class QuintillionFingers extends Upgrade {

    public QuintillionFingers(CookieClicker game) {
        super(game, 82);
        this.cost = 10000000000000.;
        productionsRequirements.put(Buildings.CURSOR, 250);

        icon = new MaterialData(Material.ARROW).toItemStack();
        icon.setAmount(1);

        gain = "+5000";
        loadLanguage(UpgradeType.GAIN_MOUSE_AND_OTHER, Buildings.CURSOR);
    }

    @Override
    public void onActivation(CCGame game) {
        for (Buildings buildings : Buildings.values()) {
            if (buildings == Buildings.CURSOR) continue;
            game.addBuildingBonus(Buildings.CURSOR, buildings, 5000);
            game.addClickBonus(buildings, 5000);
        }
    }


}
