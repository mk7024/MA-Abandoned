package main.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                    playerteam.sendMessage(ChatColor.GRAY + "["+ ChatColor.RED + "TEAM" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName() + ": " + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.BLUE){
                    playerteam.sendMessage(ChatColor.GRAY + "["+ ChatColor.BLUE + "TEAM" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName() + ": " + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.GREEN){
                    playerteam.sendMessage(ChatColor.GRAY + "["+ ChatColor.GREEN + "TEAM" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName() + ": " + event.getMessage());
                }
                if(main.TeamManager.getTeamList(playerteam) == main.TeamManager.YELLOW){
                    playerteam.sendMessage(ChatColor.GRAY + "["+ ChatColor.YELLOW + "TEAM" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName() + ": " + event.getMessage());
                }
            }
        }else{
            Bukkit.broadcastMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "ALL" + ChatColor.GRAY + "]" + " [" + ChatColor.DARK_PURPLE + "LOBBY" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName() + ": " + event.getMessage());
        }
        event.setCancelled(true);
    }
}
