package main.listener;

import main.GameManager;
import main.TeamManager;
import net.minecraft.server.v1_12_R1.ItemMapEmpty;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Colorable;
import org.bukkit.material.Dye;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

import static main.TeamType.*;

public class playerinteractevent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(GameManager.getState() == 0){
            Player player = event.getPlayer();
            ItemStack block = player.getInventory().getItemInMainHand();
            if(GameManager.getState() == 0) {
                if (block.getType() == Material.WOOL) {
                        short dura = block.getDurability();
                        if (dura == 11) {
                            TeamManager.addToTeam(BLUE, player);
                        } else if (dura == 5) {
                            TeamManager.addToTeam(GREEN, player);
                        } else if (dura == 14) {
                            TeamManager.addToTeam(RED, player);
                        } else if (dura == 4) {
                            TeamManager.addToTeam(YELLOW, player);
                        }
                    event.setCancelled(true);
                    }

            }
        }

    }
}
