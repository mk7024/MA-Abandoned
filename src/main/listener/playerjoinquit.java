package main.listener;

import main.GameManager;
import main.MA;
import main.TeamManager;
import main.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import static main.TeamManager.setTitleName;

public class playerjoinquit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent p){
        Player player = p.getPlayer();
        player.getInventory().clear();
        GameManager.setprimaryitem(player);
        if(main.GameManager.getState() <= 3){
            p.setJoinMessage(MA.prefix + player.getName() + "加入了游戏!");
        }else {
            player.kickPlayer(ChatColor.RED + "现在已经是" + ChatColor.GREEN + String.valueOf(main.GameManager.getState()));
        }
        if(main.TeamManager.isInTeam(player)){
            setTitleName(player);
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent p){
        Player player = p.getPlayer();
        if(ScoreHelper.hasScore(p.getPlayer())){
            ScoreHelper.removeScore(p.getPlayer());
        }
        p.setQuitMessage(MA.prefix + player.getName() + "退出了游戏!");
        if(main.GameManager.getState() == 0){
            TeamManager.removeFromTeam(player);
        }
    }

}
