package main.utils;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class isSoulBound {
    public static boolean checkSoulBound(ItemStack item){
        if (item == null || item.getItemMeta() == null || item.getLore() == null) {
            return false;
        }
        List<String> lore = item.getLore();
        for(String s : lore) {
            if(s.contains("Soulbound")) {
                return true;
            }
        }
        return false;
    }
}
