package Listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import AnyPack.Inicio;
import AnyPack.Perms;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;

public class Listeners implements Listener{
	Perms pm = new Perms();
	private Inicio plugin;
	public Listeners(Inicio plugin) {
		this.plugin=plugin;
	}
	
	public Listeners() {
	}
	@EventHandler
	public void cuando_entra(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String entra = "cuando_entra";
		String entra_admin = "cuando_entra_admin";
		String primer = "titulo_entra";
		String segundo = "subtitulo_entra";
		player.sendTitle(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(primer).replace("%nombre%", player.getName())), ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(segundo).replace("%nombre%", player.getName())));
    	if(player.isOp() || player.hasPermission(pm.admin_message)) {
    		event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(entra_admin))));
        String console_alert = "console_message";
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(console_alert).replace("%nombre%", player.getName())));
    	}else {
    		event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(entra))));
    		}
    	List<String> messages = plugin.getConfig().getStringList("mensaje_hacia_jugador");
    	for(int i=0;i<messages.size();i++) {
    		
    		String text = messages.get(i);
    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    	}
	}
	
	
	@EventHandler
	public void Sale_Jugador(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	String sale = "cuando_sale";
		event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(sale))));
    	
    }
	
	
	@EventHandler
	public void cuando_duerme(PlayerBedEnterEvent event) {
		String respawn = "bed_respawn";
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(respawn))));
	}
	@EventHandler
	public void Respawn(PlayerRespawnEvent event) {
		String respawn = "mensaje_respawn";
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(respawn))));
		
	}
	
	
	@EventHandler
	public void ChatPL(AsyncPlayerChatEvent event) {
		String lpc = "luckperms_chat";
		boolean luckperms_chat = plugin.getConfig().getBoolean(lpc);
		String cooldown_msg = "cooldown_msg";
		Player player = event.getPlayer();
		String toxic_msg = "toxic_msg";
		List<String> block = plugin.getConfig().getStringList("mensajes_bloqueados");
    	for(int i=0;i<block.size();i++) {
    		
    		String text = block.get(i);
    		if (event.getMessage().equalsIgnoreCase(text)) {
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(toxic_msg))));
    			event.setCancelled(true);
    			break;
    		}
    		
    		}
    	if (luckperms_chat) {
    		String prefix = "prefix_chat";
    		event.setFormat(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString(prefix)+player.getName()+"&f: &7"+event.getMessage())));
    	
    	}
    	
    	}
	}

