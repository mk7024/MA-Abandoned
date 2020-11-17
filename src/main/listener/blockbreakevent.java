package main.listener;

import main.GameManager;
import org.bukkit.*;
import org.bukkit.block.Block;
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
        if(GameManager.getState() != 0){
            if(block.getType() == Material.COAL_ORE){
                restore(block,block.getType(),5);
        }else {
                if(block.getType() == Material.ENDER_STONE){

                }
            }
            b.setCancelled(true);
        }
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
