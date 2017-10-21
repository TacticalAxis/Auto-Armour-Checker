package tacticalaxis.aac;

import org.bukkit.configuration.file.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class Function implements CommandExecutor {
    public FileConfiguration config;

    public Function(final Main p) {
        this.config = p.getConfig();
    }

    public String translate(final String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final String offlineplayermessage = (this.translate(this.config.getString("offline-player-message"))).toString();
        final String armourremovemessage = (this.translate(this.config.getString("armour-remove-message"))).toString();
        final String nopermissionmessage = (this.translate(this.config.getString("no-permission-message"))).toString();
        final String commandusagemessage = (this.translate(this.config.getString("cmd-usage-message"))).toString();
        final String consolemessage = (this.translate(this.config.getString("console-message"))).toString();
        final String pluginname = (this.translate(this.config.getString("plugin-name"))).toString();
        final String servernameVar = (this.config.getString("server-name")).toString();
        final String servernameparsed = (servernameVar.replace(" ", "")).toString();
        final String arrowVar = (this.config.getString("arrow-head")).toString();
        final String arrowheadparsed = (arrowVar.replace("", "")).toString();
        final String server = (this.translate(servernameparsed + " " + arrowheadparsed + " ")).toString();
        if (command.getName().equalsIgnoreCase("ac")) {
            if (sender instanceof Player) {
                final Player p = (Player) sender;
                if (args.length > 0) {
                    if (p.hasPermission("aac.use")) {
                        if (args.length > 1) {
                            p.sendMessage(" ");
                            p.sendMessage(" ");
                            p.sendMessage("  ");
                            p.sendMessage("§2§l§m=============================================");
                            p.sendMessage(server + pluginname);
                            p.sendMessage(commandusagemessage);
                            p.sendMessage("§2§l§m=============================================");
                        } else {
                            final Player t = Bukkit.getServer().getPlayer(args[0]);
                            if (t == null) {
                                p.sendMessage(server + offlineplayermessage);
                            } else {
                                this.removeArmor(t, p);
                                p.sendMessage(server + armourremovemessage.replace("{target}", t.getName()));
                            }
                        }
                    } else {
                        p.sendMessage(server + nopermissionmessage);
                    }
                } else if (args.length == 0) {
                    if (p.hasPermission("aac.use")) {
                        p.sendMessage("  ");
                        p.sendMessage(" ");
                        p.sendMessage(" ");
                        p.sendMessage("§2§l§m=============================================");
                        p.sendMessage(server + pluginname);
                        p.sendMessage(commandusagemessage);
                        p.sendMessage("§2§l§m=============================================");
                    } else {
                        p.sendMessage(server + nopermissionmessage);
                    }
                }
            } else {
                sender.sendMessage(server + consolemessage);
            }
        }
        return false;
    }

    public boolean hasFullInv(final Player p) {
        ItemStack[] contents;
        for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack item = contents[i];
            if (item == null) {
                return false;
            }
        }
        return true;
    }

    public void removeArmor(final Player player, final Player sender) {
        sender.getDisplayName();
        final ItemStack helmet = player.getInventory().getHelmet();
        if (helmet != null) {
            if (this.hasFullInv(player)) {
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (ItemStack content : armorContents) {
                    if (content.getAmount() != 0) {
                        player.getWorld().dropItemNaturally(player.getLocation(), content);
                    }
                }
                player.getInventory().setArmorContents(new ItemStack[4]);
            } else {
                player.getInventory().addItem(helmet);
                player.getInventory().setHelmet(null);
            }
        }
        final ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate != null) {
            if (this.hasFullInv(player)) {
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (ItemStack content : armorContents) {
                    if (content.getAmount() != 0) {
                        player.getWorld().dropItemNaturally(player.getLocation(), content);
                    }
                }
                player.getInventory().setArmorContents(new ItemStack[4]);
            } else {
                player.getInventory().addItem(chestplate);
                player.getInventory().setChestplate(null);
            }
        }
        final ItemStack leggings = player.getInventory().getLeggings();
        if (leggings != null) {
            if (this.hasFullInv(player)) {
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (ItemStack content : armorContents) {
                    if (content.getAmount() != 0) {
                        player.getWorld().dropItemNaturally(player.getLocation(), content);
                    }
                }
                player.getInventory().setArmorContents(new ItemStack[4]);
            } else {
                player.getInventory().addItem(leggings);
                player.getInventory().setLeggings(null);
            }
        }
        final ItemStack boots = player.getInventory().getBoots();
        if (boots != null) {
            if (this.hasFullInv(player)) {
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (ItemStack content : armorContents) {
                    if (content.getAmount() != 0) {
                        player.getWorld().dropItemNaturally(player.getLocation(), content);
                    }
                }
                player.getInventory().setArmorContents(new ItemStack[4]);
            } else {
                player.getInventory().addItem(boots);
                player.getInventory().setBoots(null);
            }
        }
    }
}
