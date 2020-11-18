package main;

import main.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import static main.TeamType.*;

public class GameManager {
    private static short state= 0;
    private static short maxplayersperteam = 8;
    private static short minplayersonline = 1;
    private static short phase1time = 300;
    private static int redteamhealth,yellowteamhealth,greenteamhealth,blueteamhealth;

    public static void checkWhenToRun(){
        new BukkitRunnable(){
            @Override
            public void run(){
                if(GameManager.getState() == 0){
                    if(Bukkit.getServer().getOnlinePlayers().size() >= minplayersonline){
                            System.out.println("游戏可以启动");
                            countToStart();
                            cancel();
                    }

                }else {
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    private static void setDefaultHealth(){
        redteamhealth = blueteamhealth = yellowteamhealth = greenteamhealth = MA.getInstance().getConfig().getInt("defaulthealth");
    }

    public static void setHealth(int health,TeamType type){
        if(type.toString().equalsIgnoreCase("GREEN")){
            greenteamhealth += health;
        }
        if(type.toString().equalsIgnoreCase("BLUE")){
            blueteamhealth += health;
        }
        if(type.toString().equalsIgnoreCase("RED")){
            redteamhealth += health;
        }
        if(type.toString().equalsIgnoreCase("YELLOW")){
            yellowteamhealth += health;
        }
    }
    public static int getHealth(TeamType type){
        if(type.toString().equalsIgnoreCase("GREEN")){
            return greenteamhealth;
        }
        if(type.toString().equalsIgnoreCase("BLUE")){
            return blueteamhealth;
        }
        if(type.toString().equalsIgnoreCase("RED")){
            return redteamhealth;
        }
        if(type.toString().equalsIgnoreCase("YELLOW")){
            return yellowteamhealth;
        }
        return 0;
    }

    public static void sendTitleToAllPlayers(String s1,String s2,int i1,int i2,int i3){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            player.sendTitle(s1,s2,i1,i2,i3);
        }
    }

    public static void startPhase1(){
        state = 1;
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(!TeamManager.isInTeam(player)){
                TeamManager.startToBalanceTeamPlayer(player);
                main.listener.playerrespawnevent.teleportToTeamLocation(player);
            }
        }
        countPhase1();
        setDefaultHealth();
        setphase1board();
    }

    public static void startPhase2(){
        state = 2;
    }

    private static void countToStart(){
        new BukkitRunnable(){
            int timetocount = 15;
            @Override
            public void run(){
                timetocount -= 1;
                if(Bukkit.getServer().getOnlinePlayers().size() < minplayersonline){
                    checkWhenToRun();
                    System.out.println("启动cancel");
                    sendTitleToAllPlayers(ChatColor.RED + "人数不足,停止计时!","",10,20,10);
                    cancel();
                }
//                if(timetocount == 60){
//                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有60秒开始","",10,20,10);
//                }
                if(timetocount == 30){
                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有30秒开始","",10,20,10);
                }
                if(timetocount == 10){
                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有10秒开始","",10,20,10);
                }
                if(timetocount == 5){
                    sendTitleToAllPlayers(ChatColor.GREEN + "5","",5,10,5);
                }
                if(timetocount == 4){
                    sendTitleToAllPlayers(ChatColor.GREEN + "4","",5,10,5);
                }
                if(timetocount == 3){
                    sendTitleToAllPlayers(ChatColor.GREEN + "3","",5,10,5);
                }
                if(timetocount == 2){
                    sendTitleToAllPlayers(ChatColor.GREEN + "2","",5,10,5);
                }
                if(timetocount == 1){
                    sendTitleToAllPlayers(ChatColor.GREEN + "1","",5,10,5);
                }
                if(timetocount == 0){
                    startPhase1();
                    cancel();
                }
                //TODO
                System.out.println(timetocount);
            }
        }.runTaskTimerAsynchronously(MA.getInstance(),0,20);
    }

    public static short getState(){
        return state;
    }

    public static void setphase1board(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getState() == 1) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        ScoreHelper helper = ScoreHelper.createScore(player);
                        helper.setTitle("Mini Annihilation");
                        int time = phase1time;
                        int min = time/60;
                        int sec = (time - time/60*60)%60;
                        helper.setSlot(5, "&a阶段1时间:" + ChatColor.RESET + min + ":" + sec);
                        helper.setSlot(4, "&a红队:" + ChatColor.RESET + redteamhealth);
                        helper.setSlot(3, "&a蓝队:" + ChatColor.RESET+ blueteamhealth);
                        helper.setSlot(2, "&a黄队:" + ChatColor.RESET+ yellowteamhealth);
                        helper.setSlot(1, "&a绿队:"+ ChatColor.RESET + greenteamhealth);
                    }
                }else cancel();
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    private static void countPhase1(){
        new BukkitRunnable(){
            @Override
            public void run(){
                phase1time -= 1;
                if(phase1time == 0){
                    startPhase2();
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    public static void setandupdateprimaryboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getState() == 0) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        ScoreHelper helper = ScoreHelper.createScore(player);
                        helper.setTitle("Mini Annihilation");
                        helper.setSlot(5, "&7玩家:" + "&e" + Bukkit.getServer().getOnlinePlayers().size() + "/24");
                        helper.setSlot(4, "&a红队:" + TeamManager.getTeamSize(RED));
                        helper.setSlot(3, "&a蓝队:" + TeamManager.getTeamSize(BLUE));
                        helper.setSlot(2, "&a黄队:" + TeamManager.getTeamSize(YELLOW));
                        helper.setSlot(1, "&a绿队:" + TeamManager.getTeamSize(GREEN));
                    }
                }else cancel();
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    public static void setprimaryitem(Player p){
        Player player = p.getPlayer();
        player.getInventory().clear();
        ItemStack[] selectitem = new ItemStack[4];
        ItemMeta[] selectitemcolor = new ItemMeta[4];

        selectitem[0] = new ItemStack(Material.WOOL,1,(byte) 14);
        selectitemcolor[0] = selectitem[0].getItemMeta();
        selectitemcolor[0].setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "加入红队");
        selectitem[0].setItemMeta(selectitemcolor[0]);

        selectitem[1] = new ItemStack(Material.WOOL,1,(byte) 5);
        selectitemcolor[1] = selectitem[1].getItemMeta();
        selectitemcolor[1].setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "加入绿队");
        selectitem[1].setItemMeta(selectitemcolor[1]);

        selectitem[2] = new ItemStack(Material.WOOL,1,(byte) 11);
        selectitemcolor[2] = selectitem[2].getItemMeta();
        selectitemcolor[2].setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "加入蓝队");
        selectitem[2].setItemMeta(selectitemcolor[2]);

        selectitem[3] = new ItemStack(Material.WOOL,1,(byte) 4);
        selectitemcolor[3] = selectitem[3].getItemMeta();
        selectitemcolor[3].setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "加入黄队");
        selectitem[3].setItemMeta(selectitemcolor[3]);

        player.getInventory().setContents(selectitem);
    }
}
