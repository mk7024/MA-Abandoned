package main;

import main.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MA extends JavaPlugin {
    private static MA instance;
    public static String prefix = ChatColor.GREEN + "[MA]" + ChatColor.RESET;
    @Override
    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        GameManager.checkWhenToRun();
        GameManager.setandupdateprimaryboard();
        getCommand("MA").setExecutor(new main.command.gameSet());
        getServer().getPluginManager().registerEvents(new playerjoinquit(),this);
        getServer().getPluginManager().registerEvents(new blockbreakevent(),this);
        getServer().getPluginManager().registerEvents(new playerinteractevent(),this);
        getServer().getPluginManager().registerEvents(new entitydamageevent(),this);
        getServer().getPluginManager().registerEvents(new inventoryclickevent(),this);
        System.out.println("MiniAnnihilation插件已启动");
    }

    @Override
    public void onDisable(){
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("MiniAnnihilation插件已关闭");
    }


    public static MA getInstance(){
        return instance;
    }

}
