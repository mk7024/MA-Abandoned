package main.listener;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

public class SoulBoundItem implements Listener {
    @EventHandler
    public void onQuitItem(PlayerDropItemEvent event) {
        if (main.utils.isSoulBound.checkSoulBound(event.getItemDrop().getItemStack())) {
            event.getItemDrop().remove();
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_IRONGOLEM_DEATH, 1, 2f);
        }
    }

    @EventHandler
    public void onInventoryEvent(InventoryClickEvent e) {
            if(e.getCurrentItem() == null) {
                return;
            }
            if(main.utils.isSoulBound.checkSoulBound(e.getCursor()) || main.utils.isSoulBound.checkSoulBound(e.getCurrentItem())) {
                if(e.getInventory().getType() != InventoryType.CRAFTING) {
                    if(e.getSlot() == e.getRawSlot()) {
                        e.setCancelled(true);
                    }
                    if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                        e.setCancelled(true);
                    }

                }
                if((e.getSlotType() != InventoryType.SlotType.CONTAINER && e.getSlotType() != InventoryType.SlotType.QUICKBAR)
                ) {
                    e.setCancelled(true);
                }
            }
        }
    }
