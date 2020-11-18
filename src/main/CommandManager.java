package main;

import static org.bukkit.Bukkit.getServer;

public class CommandManager {
    public static void setTeamSpawn(String string,double x,double y, double z){
        MA.getInstance().getConfig().set("Team." + string + ".spawnpoint.x", x);
        MA.getInstance().getConfig().set("Team." + string + ".spawnpoint.y", y);
        MA.getInstance().getConfig().set("Team." + string + ".spawnpoint.z", z);
        MA.getInstance().saveConfig();
    }
    public static void setTeamEndStone(String string,double x,double y,double z){
        MA.getInstance().getConfig().set("Team." + string + ".end_stone.x", x);
        MA.getInstance().getConfig().set("Team." + string + ".end_stone.y", y);
        MA.getInstance().getConfig().set("Team." + string + ".end_stone.z", z);
        MA.getInstance().saveConfig();
    }
    public static void setWorld(String string){
        MA.getInstance().getConfig().set("gameworldname",string);
        MA.getInstance().saveConfig();
    }
    public static void setHealth(int health){
        MA.getInstance().getConfig().set("defaulthealth",health);
        MA.getInstance().saveConfig();
    }
}
