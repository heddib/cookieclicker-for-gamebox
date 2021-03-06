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
public class CarpalTunnelPreventionCream extends Upgrade {

    public CarpalTunnelPreventionCream(CookieClicker game) {
        super(game, 1);
        this.cost = 500;
        productionsRequirements.put(Buildings.CURSOR, 1);

        icon = new MaterialData(Material.ARROW).toItemStack();
        icon.setAmount(1);

        loadLanguage(UpgradeType.CLASSIC_MOUSE, Buildings.CURSOR);
    }

    @Override
    public void onActivation(CCGame game) {
        game.baseCookiesPerClick = game.baseCookiesPerClick * 2;
        game.getBuilding(Buildings.CURSOR).multiply(game.getGameUuid(), 2);
        game.getBuilding(Buildings.CURSOR).visualize(game);
    }


}
