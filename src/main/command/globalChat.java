package main.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class globalChat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args){
        Player playersend = (Player)sender;
        if(args[0]!=null){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(playersend.getName() + "" + args[0]);
            }
        }
        return true;
    }
}
