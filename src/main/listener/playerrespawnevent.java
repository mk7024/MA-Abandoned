package main.listener;

import main.MA;
import main.TeamManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class playerrespawnevent {
    @EventHandler
    public void onResspawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if(TeamManager.getTeamHealth(player) == 0){
            player.setGameMode(GameMode.SPECTATOR);
        }
        TeamManager.teleportToTeamLocation(player);
    }

}
