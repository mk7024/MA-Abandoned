package main.listener;

import main.GameManager;
import main.MA;
import main.TeamManager;
import main.TeamType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class blockbreakevent implements Listener {
//    HashMap<Block, Location> restoreblock = new HashMap<>();
    World world = Bukkit.getWorld(MA.getInstance().getConfig().getString("gameworldname"));
    Location redteamendstone = new Location(world,MA.getInstance().getConfig().getDouble("Team.RED.end_stone.x"),MA.getInstance().getConfig().getDouble("Team.RED.end_stone.y"),MA.getInstance().getConfig().getDouble("Team.RED.end_stone.z"));
    Location blueteamendstone = new Location(world,MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.x"),MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.y"),MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.z"));
    Location yellowteamendstone = new Location(world,MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.x"),MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.y"),MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.z"));
    Location greenteamendstone = new Location(world,MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.x"),MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.y"),MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.z"));
    @EventHandler
    public void onBreak(BlockBreakEvent b) {
        Block block = b.getBlock();
        Player player = b.getPlayer();

        if (GameManager.getState() != 0) {
            if (block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.STONE || block.getType() == Material.GOLD_ORE || block.getType() == Material.DIAMOND_ORE) {
                restore(block, block.getType(), 5);
            }
            if (block.getType() == Material.ENDER_STONE) {
                Location location = block.getLocation();
                main.GameManager.setphase1board(player);
                if (location.equals(redteamendstone)) {
                    if (!main.TeamManager.isInSpecificTeam(player, main.TeamManager.returnRED())) {
                        main.GameManager.setHealth(-1, TeamType.RED);
                        main.MA.sendTitleToAllPlayers("", ChatColor.RED + player.getName() + "正在破坏红队核心!", 5, 10, 5);
                        setBlock(block, Material.BEDROCK);
                        restore(block, Material.ENDER_STONE, 1);
                        b.setDropItems(false);
                    } else {
                        player.sendTitle("你不能破坏自己的核心!", "", 5, 15, 5);
                        b.setCancelled(true);
                    }
                }
                if (location.equals(blueteamendstone)) {
                    if (!main.TeamManager.isInSpecificTeam(player, main.TeamManager.returnBLUE())) {
                        main.GameManager.setHealth(-1, TeamType.BLUE);
                        main.MA.sendTitleToAllPlayers(null, player.getName() + "正在破坏蓝队核心!", 5, 10, 5);
                        setBlock(block, Material.BEDROCK);
                        restore(block, Material.ENDER_STONE, 1);
                        b.setDropItems(false);
                    } else {
                        player.sendTitle("你不能破坏自己的核心!", "", 5, 15, 5);
                        b.setCancelled(true);
                    }
                }
                if (location.equals(yellowteamendstone)) {
                    if (!main.TeamManager.isInSpecificTeam(player, main.TeamManager.returnYELLOW())) {
                        main.GameManager.setHealth(-1, TeamType.YELLOW);
                        main.MA.sendTitleToAllPlayers(null, player.getName() + "正在破坏黄队核心!", 5, 10, 5);
                        setBlock(block, Material.BEDROCK);
                        restore(block, Material.ENDER_STONE, 1);
                        b.setDropItems(false);
                    } else {
                        player.sendTitle("你不能破坏自己的核心!", "", 5, 15, 5);
                        b.setCancelled(true);
                    }
                }
                if (location.equals(greenteamendstone)) {
                    if (!main.TeamManager.isInSpecificTeam(player, main.TeamManager.returnGREEN())) {
                        main.GameManager.setHealth(-1, TeamType.RED);
                        main.MA.sendTitleToAllPlayers(null, player.getName() + "正在破坏绿队核心!", 5, 10, 5);
                        setBlock(block, Material.BEDROCK);
                        restore(block, Material.ENDER_STONE, 1);
                        b.setDropItems(false);
                    } else {
                        player.sendTitle("你不能破坏自己的核心!", "", 5, 15, 5);
                        b.setCancelled(true);
                    }
                }
            }
        }else b.setCancelled(true);
    }

    public void restore(Block b,Material material, int time){
        Chunk chunk = b.getChunk();
        if(!chunk.isLoaded()){
            chunk.load();
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.MA.getInstance(), new Runnable() {
            @Override
            public void run() {
                b.setType(material);
                System.out.println("恢复成功");
                System.out.println(material);
            }
        },(time * 20));
    }
    public void setBlock(Block block,Material material){
        new BukkitRunnable(){
            @Override
            public void run(){
                block.setType(material);
            }
        }.runTaskLater(MA.getInstance(),2);
    }
}
