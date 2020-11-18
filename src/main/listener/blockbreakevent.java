package main.listener;

import main.GameManager;
import main.MA;
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
    @EventHandler
    public void onBreak(BlockBreakEvent b){
        Block block = b.getBlock();
        Player player = b.getPlayer();
        if(GameManager.getState() != 0){
            if(block.getType() == Material.COAL_ORE){
                restore(block,block.getType(),5);
        }else {
                if(block.getType() == Material.ENDER_STONE && GameManager.getState() >=1){
                    double x = block.getLocation().getX();
                    double y = block.getLocation().getY();
                    double z = block.getLocation().getZ();
                    if(x == MA.getInstance().getConfig().getDouble("Team.RED.end_stone.x") && y == MA.getInstance().getConfig().getDouble("Team.RED.end_stone.y") && z == MA.getInstance().getConfig().getDouble("Team.RED.end_stone.z")){
                        main.GameManager.setHealth(-1, TeamType.RED);
                        main.GameManager.sendTitleToAllPlayers(null,player.getName() + "正在破坏红队核心!",5,10,5);
                    }
                    if(x == MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.x") && y == MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.y") && z == MA.getInstance().getConfig().getDouble("Team.BLUE.end_stone.z")){
                        main.GameManager.setHealth(-1, TeamType.BLUE);
                        main.GameManager.sendTitleToAllPlayers(null,player.getName() + "正在破坏蓝队核心!",5,10,5);
                    }
                    if(x == MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.x") && y == MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.y") && z == MA.getInstance().getConfig().getDouble("Team.YELLOW.end_stone.z")){
                        main.GameManager.setHealth(-1, TeamType.YELLOW);
                        main.GameManager.sendTitleToAllPlayers(null,player.getName() + "正在破坏黄队核心!",5,10,5);
                    }
                    if(x == MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.x") && y == MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.y") && z == MA.getInstance().getConfig().getDouble("Team.GREEN.end_stone.z")){
                        main.GameManager.setHealth(-1, TeamType.RED);
                        main.GameManager.sendTitleToAllPlayers(null,player.getName() + "正在破坏绿队核心!",5,10,5);
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
}
