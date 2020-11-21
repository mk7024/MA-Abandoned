package main.listener;

import main.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryclickevent implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent click){
        Player player = (Player) click.getWhoClicked();
        if(GameManager.getState() <= 3 && !main.TeamManager.isInTeam(player)) {
        click.setCancelled(true);
        }
    }
}
