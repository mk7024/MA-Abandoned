package main.command;

import main.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class globalChat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args){
        Player playersend = (Player)sender;
        if(args[0]!=null){
            for(Player player : Bukkit.getOnlinePlayers()){
                if(TeamManager.RED.contains(playersend)){
                    player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "ALL" + ChatColor.GRAY + "]" + "  [" + ChatColor.RED + "RED" + ChatColor.GRAY + "]  " + ChatColor.RESET + player.getName() + ":  " + args[0]);
                }
                if(TeamManager.YELLOW.contains(playersend)){
                    player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "ALL" + ChatColor.GRAY + "]" + "  [" + ChatColor.YELLOW + "YELLOW" + ChatColor.GRAY + "]  " + ChatColor.RESET + player.getName() + ":  " + args[0]);
                }
                if(TeamManager.BLUE.contains(playersend)){
                    player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "ALL" + ChatColor.GRAY + "]" + "  [" + ChatColor.BLUE + "BLUE" + ChatColor.GRAY + "]  " + ChatColor.RESET + player.getName() + ":  " + args[0]);
                }
                if(TeamManager.GREEN.contains(playersend)){
                    player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "ALL" + ChatColor.GRAY + "]" + "  [" + ChatColor.GREEN + "GREEN" + ChatColor.GRAY + "]  " + ChatColor.RESET + player.getName() + ":  " + args[0]);
                }
            }
        }
        return true;
    }
}
