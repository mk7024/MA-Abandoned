package main.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class gameSet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        Player player = (Player) sender;
        if(args[0].equalsIgnoreCase("")){
            return false;
        }
        if(args[0].equalsIgnoreCase("setBlueSpawn")){
            main.CommandManager.setTeamSpawn("BLUE",player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
        }
        if(args[0].equalsIgnoreCase("setGreenSpawn")){
            main.CommandManager.setTeamSpawn("GREEN",player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
        }
        if(args[0].equalsIgnoreCase("setYellowSpawn")){
            main.CommandManager.setTeamSpawn("YELLOW",player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
        }
        if(args[0].equalsIgnoreCase("setREDSpawn")){
            main.CommandManager.setTeamSpawn("RED",player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
        }
        if(args[0].equalsIgnoreCase("setBlueEndStone")){
            main.CommandManager.setTeamEndStone("BLUE",player.getTargetBlock((Set<Material>) null,5).getX(),player.getTargetBlock((Set<Material>) null,5).getY(),player.getTargetBlock((Set<Material>) null,5).getZ());
        }
        if(args[0].equalsIgnoreCase("setGREENEndStone")){
            main.CommandManager.setTeamEndStone("GREEN",player.getTargetBlock((Set<Material>) null,5).getX(),player.getTargetBlock((Set<Material>) null,5).getY(),player.getTargetBlock((Set<Material>) null,5).getZ());
        }
        if(args[0].equalsIgnoreCase("setYELLOWEndStone")){
            main.CommandManager.setTeamEndStone("YELLOW",player.getTargetBlock((Set<Material>) null,5).getX(),player.getTargetBlock((Set<Material>) null,5).getY(),player.getTargetBlock((Set<Material>) null,5).getZ());
        }
        if(args[0].equalsIgnoreCase("setREDEndStone")){
            main.CommandManager.setTeamEndStone("RED",player.getTargetBlock((Set<Material>) null,5).getX(),player.getTargetBlock((Set<Material>) null,5).getY(),player.getTargetBlock((Set<Material>) null,5).getZ());
        }
        if(args[0].equalsIgnoreCase("setgameworld")){
            main.CommandManager.setWorld(player.getWorld().getName());
        }
        if(args[0].equalsIgnoreCase("setdefaulthealth")){
            if(args[1] != null) {
                main.CommandManager.setHealth(Integer.parseInt(args[1]));
            }else return false;
        }
        System.out.println(cmd + "已完成!");
        return true;
    }
}
