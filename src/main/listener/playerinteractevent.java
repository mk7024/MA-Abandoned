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
        Player player = event.getPlayer();
        if(GameManager.getState() <=3 && !main.TeamManager.isInTeam(player)){
            ItemStack block = player.getInventory().getItemInMainHand();
                if (block.getType() == Material.WOOL) {
                        short dura = block.getDurability();
                        player.getInventory().clear();
                        if (dura == 11) {
                            if(GameManager.getHealth(BLUE) != 0){
                                TeamManager.addToTeam(BLUE, player);
                            }else {
                                player.sendMessage("蓝队已经死亡,无法加入!");
                            }
                        } else if (dura == 5) {
                            if(GameManager.getHealth(GREEN) != 0){
                                TeamManager.addToTeam(GREEN, player);
                            }else {
                                player.sendMessage("绿队已经死亡,无法加入!");
                            }
                        } else if (dura == 14) {
                            if(GameManager.getHealth(RED) != 0){
                                TeamManager.addToTeam(RED, player);
                            }else {
                                player.sendMessage("红队已经死亡,无法加入!");
                            }
                        } else if (dura == 4) {
                            if(GameManager.getHealth(YELLOW) != 0){
                                TeamManager.addToTeam(YELLOW, player);
                            }else {
                                player.sendMessage("黄队已经死亡,无法加入!");
                            }
                        }
                    }
                if(GameManager.getState() >=1 && GameManager.getState() <=3 && main.TeamManager.isInTeam(player)){
                    main.TeamManager.teleportToTeamLocation(player);
                }
            event.setCancelled(true);
        }

    }
}
