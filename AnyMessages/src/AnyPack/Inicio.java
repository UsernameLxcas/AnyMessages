package AnyPack;

import java.io.File;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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
import Listeners.Listeners;
import Commands.Toxic;
import Commands.reload;
public class Inicio extends JavaPlugin implements Listener {
	Toxic toxic = new Toxic();
	
	String carpeta;
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"AnyMessages se esta desactivando...");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"AnyMessages fue desactivado con exito!");

	}
	@Override
	public void onEnable() {
		Comando();
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"AnyMessages esta cargando...");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"AnyMessages ha sido añadido con exito!");
    	Configuracion();
    	Bukkit.getPluginManager().registerEvents(this, this);
    	
    }
	
	public void Comando() {
		this.getCommand("toxic").setExecutor(new Toxic(this));
		this.getCommand("anymsg").setExecutor(new reload(this));
	}
	
	public void Configuracion() {
		File config = new File(this.getDataFolder(),"config.yml");
		carpeta = config.getPath();
		if(!config.exists()){
    	this.getConfig().options().copyDefaults(true);
    	saveDefaultConfig();
		}
    }


}
