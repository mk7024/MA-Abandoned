package main;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
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
        if(getTeamList(player) != null){
            removeFromTeam(player);
        }
        if(type.equals(TeamType.BLUE)){
            BLUE.add(player.getName());
        }else if(type.equals(TeamType.YELLOW)){
            YELLOW.add(player.getName());
        }else if(type.equals(TeamType.RED)){
            RED.add(player.getName());
        }else {
            GREEN.add(player.getName());
        }
        setTitleName(player);
        System.out.println(RED);
        System.out.println(YELLOW);
        System.out.println(GREEN);
        System.out.println(BLUE);
    }

    public static void setTitleName(Player player){
        if(getTeamList(player) == BLUE){
            player.setDisplayName(ChatColor.BLUE + "[蓝队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.BLUE + "[蓝队]" + ChatColor.RESET + player.getName());
        }else if(getTeamList(player) == YELLOW){
            player.setDisplayName(ChatColor.YELLOW + "[黄队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.YELLOW + "[黄队]" + ChatColor.RESET + player.getName());
        }else if(getTeamList(player) == RED){
            player.setDisplayName(ChatColor.RED + "[红队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.RED + "[红队]" + ChatColor.RESET + player.getName());
        }else {
            player.setDisplayName(ChatColor.GREEN + "[绿队]" + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.GREEN + "[绿队]" + ChatColor.RESET + player.getName());
        }
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

//    public static String beforeStartBalanceTeamPlayer(){
//        if(RED.size() > BLUE.size()){
//            if(BLUE.size() > GREEN.size()){
//                if(GREEN.size() > YELLOW.size()){
//                    return "YELLOW";
//                }else{ return "GREEN";}
//            }else{ return "BLUE";}
//        }else {return "RED";}
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

    public static List getTeamList(Player player){
        if(isInTeam(player)){
            if(YELLOW.contains(player.getName())){
                return YELLOW;
            }else if(RED.contains(player.getName())){
                return RED;
            }else if(GREEN.contains(player.getName())){
                return GREEN;
            }else if(BLUE.contains(player.getName())){
                return BLUE;
            }
        }
        return null;
    }

    public static int getTeamHealth(Player player){
        if(isInTeam(player)){
            if(YELLOW.contains(player.getName())){
                return main.GameManager.getHealth(TeamType.YELLOW);
            }else if(RED.contains(player.getName())){
                return main.GameManager.getHealth(TeamType.RED);
            }else if(GREEN.contains(player.getName())){
                return main.GameManager.getHealth(TeamType.GREEN);
            }else if(BLUE.contains(player.getName())){
                return main.GameManager.getHealth(TeamType.BLUE);
            }
        }
        return 0;
    }

    public static boolean isInTeam(Player player){
        return YELLOW.contains(player.getName()) || RED.contains(player.getName()) || GREEN.contains(player.getName()) || BLUE.contains(player.getName());
    }
    public static void teleportToTeamLocation(Player player){
        World w = MA.getInstance().getServer().getWorld(MA.getInstance().getConfig().getString("gameworldname"));
        Location location = new Location(w,0,0,0);
        Chunk chunk;
        if(main.TeamManager.getTeamList(player) == BLUE){
            location.setX(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.z"));
            chunk = location.getChunk();
            chunk.isLoaded();
        }else if(main.TeamManager.getTeamList(player) == YELLOW){
            location.setX(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.z"));
            chunk = location.getChunk();
            chunk.isLoaded();
        }else if(main.TeamManager.getTeamList(player) == RED){
            location.setX(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.z"));
            chunk = location.getChunk();
            chunk.isLoaded();
        }else {
            location.setX(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.z"));
            chunk = location.getChunk();
            chunk.isLoaded();
        }
        new BukkitRunnable(){
            @Override
            public void run(){
                player.teleport(location);
                cancel();
            }
        }.runTaskLater(MA.getInstance(),10);

    }

}