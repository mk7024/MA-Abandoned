package main.listener;

import main.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryclickevent implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent click){
        if(GameManager.getState() != 0){

        }else {
            click.setCancelled(true);
        }
    }
}
