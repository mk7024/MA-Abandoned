package main.listener;

import main.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class entitydamageevent implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(GameManager.getState() == 0 ){
            event.setCancelled(true);
        }
    }
}
