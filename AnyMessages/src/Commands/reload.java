package Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import AnyPack.Inicio;
import AnyPack.Perms;

public class reload implements CommandExecutor {
	Perms pm = new Perms();
	String reload_msg = "reload_msg";
	String no_perms_msg = "no_perm_msg";
	String console_reload_msg = "console_reload_msg";
	private Inicio plugin;
	
	public reload(Inicio plugins) {
		this.plugin = plugins;
	}
	public reload() {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if (command.getName().equalsIgnoreCase("anymsg")) {
				if(!sender.hasPermission(pm.anymsg)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(no_perms_msg)));
			}else {
				if(args.length<1) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSintaxis correcta:"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/anymsg reload"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/anymsg info"));
				}else {
					String function = args[0];
					if (function.equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(reload_msg)));
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(console_reload_msg).replace("%player%", sender.getName())));
					}else if (function.equalsIgnoreCase("info")){
						sender.sendMessage(ChatColor.YELLOW+"Este plugin fue creado por Anytown");
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Spigot:&f https://www.spigotmc.org/members/anytown.1635942/"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Github:&f https://github.com/Anytown"));
					}
				}
			}
		}
		return true;
	}

}
