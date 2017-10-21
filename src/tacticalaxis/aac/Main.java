package tacticalaxis.aac;

import org.bukkit.ChatColor;

import org.bukkit.plugin.java.*;

import java.io.*;

import org.bukkit.plugin.*;

import java.util.logging.*;

public class Main extends JavaPlugin {
    public void onEnable() {
        final PluginDescriptionFile pdf = this.getDescription();
        final Logger l = this.getLogger();
        this.getCommand("ac").setExecutor(new Function(this));
        l.info(String.valueOf(ChatColor.GOLD + pdf.getName()) + ChatColor.AQUA + " by " + ChatColor.DARK_GREEN + pdf.getAuthors() + ChatColor.GREEN + " has been enabled. You are currently running version" + ChatColor.BLUE + pdf.getVersion());
        final File c = new File(this.getDataFolder(), "config.yml");
        if (!c.exists()) {
            this.saveDefaultConfig();
        }
    }
}