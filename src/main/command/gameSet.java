package main.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class gameSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        System.out.println("测试");
        return false;
    }
}
