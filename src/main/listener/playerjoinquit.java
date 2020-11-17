package main.listener;

import main.GameManager;
import main.TeamManager;
import main.utils.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class playerjoinquit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent p){
        Player player = p.getPlayer();
        player.getInventory().clear();
        GameManager.setprimaryitem(player);
        if(main.GameManager.getState() <= 3){

        }else {
            player.kickPlayer(ChatColor.RED + "现在已经是" + ChatColor.GREEN + String.valueOf(main.GameManager.getState()));
        }
    }
    @EventHandler
    public void onQuit(PlayerJoinEvent p){
        Player player = p.getPlayer();
        if(ScoreHelper.hasScore(p.getPlayer())){
            ScoreHelper.removeScore(p.getPlayer());
        }
        TeamManager.removeFromTeam(player);
    }

}
