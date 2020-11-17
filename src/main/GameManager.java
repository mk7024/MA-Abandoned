package main;

import main.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import static main.TeamType.*;

public class GameManager {
    private static short state= 0;
    private static short maxplayersperteam = 8;
    private static short minplayersonline = 1;
    private static short phase1;

    public static void checkWhenToRun(){
        new BukkitRunnable(){
            @Override
            public void run(){
                if(GameManager.getState() == 0){
                    short minplayers = 1;
                    for(Player p : Bukkit.getServer().getOnlinePlayers()){
                        short temp = 0;
                        temp += 1;
                        if(temp >= minplayers){
                            System.out.println("游戏可以启动");
                            countToStart();
                            cancel();
                        }
                    }

                }else {
                    cancel();
                }
            }
        }.runTaskTimer(MA.getInstance(),0,20);
    }

    public static void sendTitleToAllPlayers(String s1,String s2,int i1,int i2,int i3){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            player.sendTitle(s1,s2,i1,i2,i3);
        }
    }

    public static void startPhase1(){
//        state = 1;
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(!TeamManager.isInTeam(player)){
                TeamManager.startToBalanceTeamPlayer(player);
            }

        }
    }

    public static void countToStart(){
        new BukkitRunnable(){
            int timetocount = 31;
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

    public static void setandupdateprimaryboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    ScoreHelper helper = ScoreHelper.createScore(player);
                    helper.setTitle("Mini Annihilation");
                    helper.setSlot(5, "&7玩家:" + "&e" + Bukkit.getServer().getOnlinePlayers().size() + "/24");
                    helper.setSlot(4, "&a红队:" + TeamManager.getTeamSize(RED));
                    helper.setSlot(3, "&a蓝队:" + TeamManager.getTeamSize(BLUE));
                    helper.setSlot(2, "&a黄队:" + TeamManager.getTeamSize(YELLOW));
                    helper.setSlot(1, "&a绿队:" + TeamManager.getTeamSize(GREEN));
                }
            }
        }.runTaskTimer(MA.getInstance(),0,40);
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
