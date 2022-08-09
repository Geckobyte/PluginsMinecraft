package de.geckobyte.bannafterdie.main;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Date;

public final class BannAfterDieCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Console output
        System.out.println("Plugin: BannAfterDie wurde gestartet.");

        //Register Config + Listener
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
    }

    @Override
    public void onDisable() {
        //Console output
        System.out.println("Plugin: BannAfterDie wurde gestoppt.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Values
        Player p = (Player) event.getEntity();
        int sec = getConfig().getInt("bannseconds");

        //Bann + Kick Player
        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getDisplayName(), "Gestorben", Date.from(Instant.now().plusSeconds(sec)), null);
        p.kickPlayer("Du wurdest für " + Integer.toString(sec/60) +  "min gebannt, da du gestorben bist.");
    }
}
