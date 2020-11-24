package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack setLore(ItemStack itemstack, List<String> lore){
        ItemMeta meta = itemstack.getItemMeta();
        if(meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(itemstack.getType());
        meta.setLore(lore);
        itemstack.setItemMeta(meta);
        return itemstack;
    }

    public static ItemStack setName(ItemStack itemstack, String name)
    {
        ItemMeta meta = itemstack.getItemMeta();
        if(meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(itemstack.getType());
        meta.setDisplayName(name);
        itemstack.setItemMeta(meta);
        return itemstack;
    }

    public static ItemStack addSoulBound(ItemStack itemstack){
        if(itemstack == null){
            return itemstack;
        }
        ItemMeta meta = itemstack.getItemMeta();
        if(meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(itemstack.getType());
        List<String> lore = meta.getLore();
        if(lore == null){
            lore = new ArrayList<String>();
        }
        lore.add(ChatColor.DARK_PURPLE + "SoulBound");
        meta.setLore(lore);
        return itemstack;
    }
}
