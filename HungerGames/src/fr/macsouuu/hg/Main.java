package fr.macsouuu.hg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.macsouuu.hg.executor.Executor;
import fr.macsouuu.hg.listerners.Listerners;
import fr.macsouuu.hg.listerners.Listerners2;

public class Main extends JavaPlugin {
	private List<Player> players = new ArrayList<>();
	private List<Location> spawns = new ArrayList<>();	
	private HgState state;
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listerners(this), this);
		PluginManager pm2 = getServer().getPluginManager();
		pm2.registerEvents(new Listerners2(this), this);
		setState(HgState.WAITING);
		getCommand("menu").setExecutor(new Executor(this));
		spawns.add(new Location(Bukkit.getWorld("lobbyhg"), 0.5, 20, 21.5, 143f, 7.9f));
		spawns.add(new Location(Bukkit.getWorld("lobbyhg"), -2.5, 20, 12.5, -0.8f, 11f));
	}
	public void setState(HgState state) {
		this.state = state;
	}
	public boolean isState(HgState state) {
		return this.state == state;
	}
	public List<Player> getPlayers(){
		return players;
	}
	public List<Location> getSpawns(){
		return spawns;
	}
}
