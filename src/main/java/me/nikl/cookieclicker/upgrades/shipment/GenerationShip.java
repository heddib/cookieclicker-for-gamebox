package me.nikl.cookieclicker.upgrades.shipment;

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
public class GenerationShip extends Upgrade {

    public GenerationShip(CookieClicker game) {
        super(game, 196);
        this.cost = 2550000000000000000.;
        productionsRequirements.put(Buildings.SHIPMENT, 150);

        icon = new MaterialData(Material.FIREWORK).toItemStack();
        icon.setAmount(1);

        loadLanguage(UpgradeType.CLASSIC, Buildings.SHIPMENT);
    }

    @Override
    public void onActivation(CCGame game) {
        game.getBuilding(Buildings.SHIPMENT).multiply(game.getGameUuid(), 2);
        game.getBuilding(Buildings.SHIPMENT).visualize(game);
    }
}
