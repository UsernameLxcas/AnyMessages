package AnyPack;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PlayerList;
import AnyPack.Perms;
public class Inicio extends JavaPlugin implements Listener {
	
	String carpeta;
	Perms pm = new Perms();
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"AnyMessages se esta desactivando...");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"AnyMessages fue desactivado con exito!");

	}
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"AnyMessages esta cargando...");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"AnyMessages ha sido añadido con exito!");
    	Configuracion();
    	Bukkit.getPluginManager().registerEvents(this, this);
    }
	
	public void Configuracion() {
		File config = new File(this.getDataFolder(),"config.yml");
		carpeta = config.getPath();
		if(!config.exists()){
    	this.getConfig().options().copyDefaults(true);
    	saveDefaultConfig();
		}
    }
	@EventHandler
	public void cuando_entra(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String entra = "cuando_entra";
		String entra_admin = "cuando_entra_admin";
		String primer = "titulo_entra";
		String segundo = "subtitulo_entra";
		player.sendTitle(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(primer).replace("%nombre%", player.getName())), ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(segundo).replace("%nombre%", player.getName())));
    	if(player.isOp() || player.hasPermission(pm.admin_message)) {
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(entra_admin).replace("%nombre%", player.getName())));
        String console_alert = "console_message";
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(console_alert).replace("%nombre%", player.getName())));
    	}else {
    	event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(entra).replace("%nombre%", player.getName())));
    	}
    	List<String> messages = this.getConfig().getStringList("mensaje_hacia_jugador");
    	for(int i=0;i<messages.size();i++) {
    		
    		String text = messages.get(i);
    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    	}
	}
	@EventHandler
	public void Sale_Jugador(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	String sale = "cuando_sale";
    	event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(sale).replace("%nombre%", player.getName())));
    	
    }
	
	@EventHandler
	public void cuando_duerme(PlayerBedEnterEvent event) {
		String respawn = "bed_respawn";
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(respawn).replace("%nombre%", player.getName())));
	}
	@EventHandler
	public void Respawn(PlayerRespawnEvent event) {
		String respawn = "mensaje_respawn";
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(respawn).replace("%nombre", player.getName())));
		
	}

}
