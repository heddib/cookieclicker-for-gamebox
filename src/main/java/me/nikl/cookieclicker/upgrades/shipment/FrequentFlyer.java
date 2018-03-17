package me.nikl.cookieclicker.upgrades.shipment;

import me.nikl.cookieclicker.CCGame;
import me.nikl.cookieclicker.buildings.Buildings;
import me.nikl.cookieclicker.upgrades.Upgrade;
import me.nikl.cookieclicker.upgrades.UpgradeType;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * @author Niklas Eicker
 */
public class FrequentFlyer extends Upgrade {

    public FrequentFlyer(CCGame game) {
        super(game, 21);
        this.cost = 2550000000000.;
        productionsRequirements.put(Buildings.SHIPMENT, 25);

        icon = new MaterialData(Material.FIREWORK).toItemStack();
        icon.setAmount(1);

        loadLanguage(UpgradeType.CLASSIC, Buildings.SHIPMENT);
    }

    @Override
    public void onActivation() {
        game.getBuilding(Buildings.SHIPMENT).multiply(2);
        game.getBuilding(Buildings.SHIPMENT).visualize(game.getInventory());
        active = true;
    }
}
