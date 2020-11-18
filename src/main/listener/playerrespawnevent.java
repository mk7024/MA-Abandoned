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
        teleportToTeamLocation(player);
    }

    public static void teleportToTeamLocation(Player player){
        World w = MA.getInstance().getServer().getWorld(MA.getInstance().getConfig().getString("gameworldname"));
        Location location = new Location(w,0,0,0);
        if(main.TeamManager.getTeam(player).contains("蓝")){
            location.setX(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.z"));
        }else if(main.TeamManager.getTeam(player).contains("黄")){
            location.setX(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.z"));
        }else if(main.TeamManager.getTeam(player).contains("红")){
            location.setX(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.z"));
        }else {
            location.setX(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.z"));
        }
        player.teleport(location);
    }
}
