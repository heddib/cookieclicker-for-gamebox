package me.nikl.cookieclicker.upgrades.factory;

import me.nikl.cookieclicker.CCGame;
import me.nikl.cookieclicker.buildings.Buildings;
import me.nikl.cookieclicker.upgrades.Upgrade;
import me.nikl.cookieclicker.upgrades.UpgradeType;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * @author Niklas Eicker
 *
 *         Custom upgrade replacing 'Sweatshop'
 */
public class Recombobulators extends Upgrade {

    public Recombobulators(CCGame game) {
        super(game, 112);
        this.cost = 650000000000.;
        productionsRequirements.put(Buildings.FACTORY, 100);

        icon = new MaterialData(Material.IRON_BLOCK).toItemStack();
        icon.setAmount(1);

        loadLanguage(UpgradeType.CLASSIC, Buildings.FACTORY);
    }

    @Override
    public void onActivation() {
        game.getBuilding(Buildings.FACTORY).multiply(2);
        game.getBuilding(Buildings.FACTORY).visualize(game.getInventory());
        active = true;
    }
}
