package main;

import main.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.MA.sendTitleToAllPlayers;
import static main.TeamType.*;

public class GameManager {
    private static short state= 0;
    private static short maxplayersperteam = 8;
    private static short minplayersonline = 1;
    private static short phase1time = 6;
    private static short phase2time = 6;
    private static short phase3time = 6;
    private static short phase4time = 6;
    public static int redteamhealth,yellowteamhealth,greenteamhealth,blueteamhealth;
    public static List<String> teamremain = new ArrayList<String>();

    public static void checkWhenToRun(){
        setandupdateprimaryboard();
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

    public static void setDefaultHealth(){
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

    private static void startPhase1(){
        state = 1;
        teamremain.add("BLUE");
        teamremain.add("YELLOW");
        teamremain.add("GREEN");
        teamremain.add("RED");
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(!TeamManager.isInTeam(player)){
                TeamManager.startToBalanceTeamPlayer(player);
            }
            main.TeamManager.teleportToTeamLocation(player);
            player.getInventory().clear();
        }
        countPhase1();
        updatephase1board();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"阶段1");
        Bukkit.broadcastMessage(ChatColor.GREEN + "保护好你的核心,同时获得矿物升级你的装备吧!");
    }

    private static void startPhase2(){
        countPhase2();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"阶段2");
        Bukkit.broadcastMessage(ChatColor.GREEN + "你可以破坏敌方阵营的核心了,上吧!");
        state = 2;
    }

    private static void startPhase3(){
        countPhase3();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"阶段3");
        Bukkit.broadcastMessage(ChatColor.GREEN + "钻石已经开放挖掘,谁能守住中心岛呢!");
        state = 3;
    }

    private static void startPhase4(){
        countPhase4();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"阶段4");
        Bukkit.broadcastMessage(ChatColor.GREEN + "最后一个阶段,每120秒核心自动损失一滴血,速战速决!");
        phase4LosingHealth();
        state = 4;
    }

    private static void countToStart(){
        new BukkitRunnable(){
            int timetocount = 7;
            @Override
            public void run(){
                timetocount -= 1;
                if(Bukkit.getServer().getOnlinePlayers().size() < minplayersonline){
                    checkWhenToRun();
                    System.out.println("启动cancel");
                    sendTitleToAllPlayers(ChatColor.RED + "人数不足,停止计时!",null,10,20,10);
                    cancel();
                }
//                if(timetocount == 60){
//                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有60秒开始","",10,20,10);
//                }
                if(timetocount == 30){
                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有30秒开始",null,10,20,10);
                }
                if(timetocount == 10){
                    sendTitleToAllPlayers(ChatColor.GREEN + "游戏还有10秒开始",null,10,20,10);
                }
                if(timetocount == 5){
                    sendTitleToAllPlayers(ChatColor.GREEN + "5",null,5,10,5);
                }
                if(timetocount == 4){
                    sendTitleToAllPlayers(ChatColor.GREEN + "4",null,5,10,5);
                }
                if(timetocount == 3){
                    sendTitleToAllPlayers(ChatColor.GREEN + "3",null,5,10,5);
                }
                if(timetocount == 2){
                    sendTitleToAllPlayers(ChatColor.GREEN + "2",null,5,10,5);
                }
                if(timetocount == 1){
                    sendTitleToAllPlayers(ChatColor.GREEN + "1",null,5,10,5);
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

    public static void updatephase1board(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getState() >=1 && getState() <=4) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        setphase1board(player);
                    }
                }else cancel();
            }
        }.runTaskTimer(MA.getInstance(),0,10);
    }

    public static void setphase1board(Player player){
        ScoreHelper helper = ScoreHelper.createScore(player);
        int time = 0,min,sec;
        if(state == 1) {
            time = phase1time;
        }else if(state == 2){
            time = phase2time;
        }else if(state == 3){
            time = phase3time;
        }else if(state == 4){
            time = phase4time;
        }
        min = time / 60;
        sec = (time - time / 60 * 60) % 60;
        helper.setTitle(ChatColor.RED + "" + ChatColor.BOLD + "核心荣耀");
        helper.setSlot(5, ChatColor.YELLOW + "" + ChatColor.BOLD + "阶段" + getState() + "时间: " + ChatColor.RESET + min + ":" + sec);
        helper.setSlot(4, ChatColor.BLUE + "" + ChatColor.BOLD+ "红队: " + ChatColor.GREEN + redteamhealth);
        helper.setSlot(3, ChatColor.BLUE + "" + ChatColor.BOLD+ "蓝队: " + ChatColor.GREEN+ blueteamhealth);
        helper.setSlot(2, ChatColor.BLUE + "" + ChatColor.BOLD+ "黄队: " + ChatColor.GREEN+ yellowteamhealth);
        helper.setSlot(1, ChatColor.BLUE + "" + ChatColor.BOLD+ "绿队: "+ ChatColor.GREEN + greenteamhealth);
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
        }.runTaskTimer(MA.getInstance(),20,20);
    }

    private static void countPhase2(){
        checkWhoToWin();
        new BukkitRunnable(){
            @Override
            public void run(){
                phase2time -= 1;
                if(phase2time == 0){
                    startPhase3();
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),20,20);
    }

    private static void countPhase3(){
        new BukkitRunnable(){
            @Override
            public void run(){
                phase3time -= 1;
                if(phase3time == 0){
                    startPhase4();
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),20,20);
    }

    private static void countPhase4(){
        new BukkitRunnable(){
            @Override
            public void run(){
                phase4time -= 1;
                if(teamremain.size() == 1){
                    cancel();
                }
                if(phase4time == 0 && teamremain.size()!=1){
                    noWinnerWhoToWin();
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),20,20);
    }

    private static void phase4LosingHealth(){
        new BukkitRunnable(){
            @Override
            public void run(){
                redteamhealth -=1;
                yellowteamhealth -=1;
                greenteamhealth -=1;
                blueteamhealth-=1;
                if(redteamhealth == 0 || yellowteamhealth == 0|| greenteamhealth ==0|| blueteamhealth == 0){
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),20*120,20*120);
    }

    private static void noWinnerWhoToWin(){

    }

    private static void setandupdateprimaryboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getState() == 0) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        ScoreHelper helper = ScoreHelper.createScore(player);
                        helper.setTitle(ChatColor.RED + "" + ChatColor.BOLD + "核心荣耀");
                        helper.setSlot(5, "&7玩家:" + "&e" + Bukkit.getServer().getOnlinePlayers().size() + "/24");
                        helper.setSlot(4, "&a红队人数:" + TeamManager.getTeamSize(RED));
                        helper.setSlot(3, "&a蓝队人数:" + TeamManager.getTeamSize(BLUE));
                        helper.setSlot(2, "&a黄队人数:" + TeamManager.getTeamSize(YELLOW));
                        helper.setSlot(1, "&a绿队人数:" + TeamManager.getTeamSize(GREEN));
                    }
                }else cancel();
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    private static void checkWhoToWin() {
        if (teamremain.size() == 1) {
            if (teamremain.equals("RED")) {
                main.TeamManager.playFirework(TeamManager.RED);
            }
            if (teamremain.equals("BLUE")) {
                main.TeamManager.playFirework(TeamManager.BLUE);
            }
            if (teamremain.equals("GREEN")) {
                main.TeamManager.playFirework(TeamManager.GREEN);
            }
            if (teamremain.equals("YELLOW")) {
                main.TeamManager.playFirework(TeamManager.YELLOW);
            }
        }
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
