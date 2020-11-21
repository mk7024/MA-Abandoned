package main.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class asyncplayerchatevent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(main.TeamManager.isInTeam(player)){
            for(Player playerteam : Bukkit.getOnlinePlayers()){
                if(main.TeamManager.getTeamList(player) == main.TeamManager.RED){
                    playerteam.sendMessage("[红队]" + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.BLUE){
                    playerteam.sendMessage("[蓝队]" + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.GREEN){
                    playerteam.sendMessage("[绿队]" + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.YELLOW){
                    playerteam.sendMessage("[黄队]" + event.getMessage());
                }
            }
        }else{
            for(Player playerall : Bukkit.getOnlinePlayers()){
                playerall.sendMessage("lobby" + event.getMessage());
            }
        }
        event.setCancelled(true);
    }
}
