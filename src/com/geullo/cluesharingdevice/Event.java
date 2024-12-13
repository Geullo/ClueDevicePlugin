package com.geullo.cluesharingdevice;

import com.google.gson.Gson;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Map;

public class Event implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BukkitScheduler schedule = main.getPlugin().getServer().getScheduler();
        BukkitRunnable run = new BukkitRunnable() {
            @Override
            public void run() {
                if (!e.getPlayer().hasPlayedBefore()) {
                    e.getPlayer().setOp(true);
                    e.getPlayer().setGameMode(GameMode.SURVIVAL);
                }
                if (e.getPlayer().getGameMode().name().equalsIgnoreCase(GameMode.SURVIVAL.name())){
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFlying(false);
                    BukkitRunnable run = new BukkitRunnable() {
                        @Override
                        public void run() {
                            sendData.sendData("cleanch", e.getPlayer(), main.getPlugin());
                        }
                    };
                    schedule.runTaskLater(main.getPlugin(),run,5);
                }
            }
        };
        schedule.runTaskLater(main.getPlugin(),run,2);
    }
    /*@EventHandler
    public void rc(PlayerInteractEvent e){
        Gson  gson = new Gson();
        e.getPlayer().sendMessage(e.getItem().serialize().toString());
        e.getPlayer().sendMessage(e.getItem().toString());
        e.getPlayer().sendMessage("sfdfdf" + ItemStack.deserialize(gson.fromJson(e.getItem().serialize().toString(),Map.class)).toString());
    }*/

}
