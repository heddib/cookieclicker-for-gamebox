package me.nikl.cookieclicker.upgrades.bank;

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
public class ScissorResistantCreditCards extends Upgrade {

    public ScissorResistantCreditCards(CookieClicker game) {
        super(game, 233);
        this.cost = 70000000;
        productionsRequirements.put(Buildings.BANK, 5);

        icon = new MaterialData(Material.GOLD_NUGGET).toItemStack();
        icon.setAmount(1);

        loadLanguage(UpgradeType.CLASSIC, Buildings.BANK);
    }

    @Override
    public void onActivation(CCGame game) {
        game.getBuilding(Buildings.BANK).multiply(game.getGameUuid(), 2);
        game.getBuilding(Buildings.BANK).visualize(game);
    }


}
