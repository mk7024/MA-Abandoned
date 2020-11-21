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
    public static List<Player> YELLOW = new ArrayList<>();
    public static List<Player> RED = new ArrayList<>();
    public static List<Player> BLUE = new ArrayList<>();
    public static List<Player> GREEN = new ArrayList<>();

    public static void addToTeam(TeamType type, Player player){
        if(getTeamList(player) != null){
            removeFromTeam(player);
        }
        if(type.equals(TeamType.BLUE)){
            BLUE.add(player);
        }else if(type.equals(TeamType.YELLOW)){
            YELLOW.add(player);
        }else if(type.equals(TeamType.RED)){
            RED.add(player);
        }else {
            GREEN.add(player);
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
        if(YELLOW.contains(player)){
            YELLOW.remove(player);
        }else if(RED.contains(player)){
            RED.remove(player);
        }else if(GREEN.contains(player)){
            GREEN.remove(player);
        }else if(BLUE.contains(player)){
            BLUE.remove(player);
        }
    }


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
            if(YELLOW.contains(player)){
                return YELLOW;
            }else if(RED.contains(player)){
                return RED;
            }else if(GREEN.contains(player)){
                return GREEN;
            }else if(BLUE.contains(player)){
                return BLUE;
            }
        }
        return null;
    }


    public static int getTeamHealth(Player player){
        if(isInTeam(player)){
            if(YELLOW.contains(player)){
                return main.GameManager.getHealth(TeamType.YELLOW);
            }else if(RED.contains(player)){
                return main.GameManager.getHealth(TeamType.RED);
            }else if(GREEN.contains(player)){
                return main.GameManager.getHealth(TeamType.GREEN);
            }else if(BLUE.contains(player)){
                return main.GameManager.getHealth(TeamType.BLUE);
            }
        }
        return 0;
    }

    public static boolean isInTeam(Player player){
        return YELLOW.contains(player) || RED.contains(player) || GREEN.contains(player) || BLUE.contains(player);
    }

    public static void teleportToTeamLocation(Player player){
        World w = MA.getInstance().getServer().getWorld(MA.getInstance().getConfig().getString("gameworldname"));
        Location location = new Location(w,0,0,0);
        if(main.TeamManager.getTeamList(player) == BLUE){
            location.setX(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.BLUE.spawnpoint.z"));
            Chunk chunk = location.getChunk();
            if(!chunk.isLoaded()){
                chunk.load(true);
            }
        }else if(main.TeamManager.getTeamList(player) == YELLOW){
            location.setX(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.YELLOW.spawnpoint.z"));
            Chunk chunk = location.getChunk();
            chunk.isLoaded();
            if(!chunk.isLoaded()){
                chunk.load(true);
            }
        }else if(main.TeamManager.getTeamList(player) == RED){
            location.setX(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.RED.spawnpoint.z"));
            Chunk chunk = location.getChunk();
            chunk.isLoaded();
            if(!chunk.isLoaded()){
                chunk.load(true);
            }
        }else {
            location.setX(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.x"));
            location.setY(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.y"));
            location.setZ(MA.getInstance().getConfig().getDouble("Team.GREEN.spawnpoint.z"));
            Chunk chunk = location.getChunk();
            chunk.isLoaded();
            if(!chunk.isLoaded()){
                chunk.load(true);
            }
        }

        new BukkitRunnable(){
            @Override
            public void run(){
                player.teleport(location);
                cancel();
            }
        }.runTaskLater(MA.getInstance(),6);

    }

}