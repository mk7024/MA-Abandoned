package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.TeamType.*;
import static main.TeamType.YELLOW;

public class TeamManager {
    private static List<String> YELLOW = new ArrayList<String>();
    private static List<String> RED = new ArrayList<String>();
    private static List<String> BLUE = new ArrayList<String>();
    private static List<String> GREEN = new ArrayList<String>();

    public static void addToTeam(TeamType type, Player player){
        if(!getTeam(player).contains("未")){
            removeFromTeam(player);
        }
        if(type.equals(TeamType.BLUE)){
            BLUE.add(player.getName());
            player.setDisplayName(ChatColor.BLUE + "[蓝队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.BLUE + "[蓝队]" + ChatColor.RESET + player.getName());
        }else if(type.equals(TeamType.YELLOW)){
            YELLOW.add(player.getName());
            player.setDisplayName(ChatColor.YELLOW + "[黄队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.YELLOW + "[黄队]" + ChatColor.RESET + player.getName());
        }else if(type.equals(TeamType.RED)){
            RED.add(player.getName());
            player.setDisplayName(ChatColor.RED + "[红队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.RED + "[红队]" + ChatColor.RESET + player.getName());
        }else {
            GREEN.add(player.getName());
            player.setDisplayName(ChatColor.GREEN + "[绿队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.GREEN + "[绿队]" + ChatColor.RESET + player.getName());
        }
        System.out.println(RED);
        System.out.println(YELLOW);
        System.out.println(GREEN);
        System.out.println(BLUE);
    }

    public static int getTeamSize(TeamType type){
        if(type.equals(TeamType.BLUE)){
            return BLUE.size();
        }else if(type.equals(TeamType.YELLOW)){
            return YELLOW.size();
        }else if(type.equals(TeamType.RED)){
            return RED.size();
        }else if(type.equals(TeamType.GREEN)){
            return GREEN.size();
        }
        return 0;
    }

    public static void removeFromTeam(Player player){
        if(YELLOW.contains(player.getName())){
            YELLOW.remove(player.getName());
        }else if(RED.contains(player.getName())){
            RED.remove(player.getName());
        }else if(GREEN.contains(player.getName())){
            GREEN.remove(player.getName());
        }else if(BLUE.contains(player.getName())){
            BLUE.remove(player.getName());
        }
    }

    public static String beforeStartBalanceTeamPlayer(){
        if(RED.size() > BLUE.size()){
            if(BLUE.size() > GREEN.size()){
                if(GREEN.size() > YELLOW.size()){
                    return "YELLOW";
                }else{ return "GREEN";}
            }else{ return "BLUE";}
        }else {return "RED";}
    }

//    public static void setDisplayNameWhenStart() {
//        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//            if (RED.contains(player.getName())) {
//                player.setDisplayName(ChatColor.RED + "[红队]" + ChatColor.RESET + player.getName());
//            } else {
//                if (YELLOW.contains(player.getName())) {
//                    player.setDisplayName(ChatColor.YELLOW + "[黄队]" + ChatColor.RESET + player.getName());
//                } else {
//                    if (BLUE.contains(player.getName())) {
//                        player.setDisplayName(ChatColor.BLUE + "[蓝队]" + ChatColor.RESET + player.getName());
//                    } else {
//                        player.setDisplayName(ChatColor.GREEN + "[绿队]" + ChatColor.RESET + player.getName());
//                    }
//                }
//            }
//        }
//    }

    public static void startToBalanceTeamPlayer(Player player){
        if(RED.size() > BLUE.size()){
            if(BLUE.size() > GREEN.size()){
                if(GREEN.size() > YELLOW.size()){
                    TeamManager.addToTeam(TeamType.YELLOW,player);
                }else{ TeamManager.addToTeam(TeamType.GREEN,player);}
            }else{  TeamManager.addToTeam(TeamType.BLUE,player);}
        }else { TeamManager.addToTeam(TeamType.RED,player);}
    }

    public static String getTeam(Player player){
        if(isInTeam(player)){
            if(YELLOW.contains(player.getName())){
                return "黄队";
            }else if(RED.contains(player.getName())){
                return "红队";
            }else if(GREEN.contains(player.getName())){
                return "绿队";
            }else if(BLUE.contains(player.getName())){
                return "蓝队";
            }
        }
            return "未加入队伍";
        }

    public static boolean isInTeam(Player player){
        return YELLOW.contains(player.getName()) || RED.contains(player.getName()) || GREEN.contains(player.getName()) || BLUE.contains(player.getName());
    }


}