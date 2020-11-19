package main.listener;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockplaceevent implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent b){
        Block block = b.getBlock();
        int x,y,z;
        x = block.getX();
        y = block.getY();
        z = block.getZ();
    }
}
