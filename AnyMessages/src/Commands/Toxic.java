package Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import AnyPack.Inicio;
import AnyPack.Perms;

public class Toxic implements CommandExecutor {
	Perms pm = new Perms();
	String no_perm_msg = "no_perm_msg";
	private Inicio plugin;
	
	public Toxic(Inicio plugins) {
		this.plugin = plugins;
	}
	public Toxic() {
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("toxic")) {
			if (!sender.hasPermission(pm.toxic_use)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(no_perm_msg)));
			}else {
				if(args.length<2) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSinstaxis correcta:"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic add <palabra>"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic rem <palabra>"));
				}else {
					if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("rem") || args[0].equalsIgnoreCase("list")) {
						if (args[0].equalsIgnoreCase("add")) {
					
							String palabra = args[1].toString();
							if (palabra==null) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSinstaxis correcta:"));
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic add <palabra>"));
				
							}
							List<String> block = plugin.getConfig().getStringList("mensajes_bloqueados");
							if(block.contains(palabra) ) {
								String no_word = "palabra_esta";
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(no_word)));
							}else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a"+palabra+" &efue añadida exitosamente!"));
							block.add(palabra);
							plugin.saveConfig();
							plugin.getConfig().set("mensajes_bloqueados", block);
							
							}
						}else if (args[0].equalsIgnoreCase("rem")){
							String palabra = args[1].toString();
							if (palabra==null) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSinstaxis correcta:"));
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic add <palabra>"));
				
							}
							List<String> block = plugin.getConfig().getStringList("mensajes_bloqueados");
							if(!block.contains(palabra) ) {
								String no_word = "palabra_no_esta";
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(no_word)));
							}else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a"+palabra+" &efue removida exitosamente!"));
							block.remove(palabra);
							plugin.getConfig().set("mensajes_bloqueados", block);
							
							}
							
						}
						
					}else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSinstaxis correcta:"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic add <palabra>"));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/toxic rem <palabra>"));
					}
				}
			}
		}
		return true;
	}

}
