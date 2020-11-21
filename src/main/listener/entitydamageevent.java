package main.listener;

import main.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class entitydamageevent implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player)event.getEntity();
            if(GameManager.getState() == 0 || (GameManager.getState() <=3 && !main.TeamManager.isInTeam(player))){
                event.setCancelled(true);
            }
        }
    }
}
