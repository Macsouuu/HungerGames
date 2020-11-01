package fr.macsouuu.hg.listerners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.macsouuu.hg.HgState;
import fr.macsouuu.hg.Main;
import fr.macsouuu.hg.start.AutoStart;

public class Listerners2 implements Listener {
	
	private Main main;
	private List<Player> specs = new ArrayList<>();
	public Listerners2(Main main) {
		this.main = main;
	}
	public void onJoin(PlayerJoinEvent e) {		
	if(main.isState(HgState.WAITING) && main.getPlayers().size() == 2) 
	{
		AutoStart start  = new AutoStart(main);
		start.runTaskTimer(main, 0, 20);
		main.setState(HgState.STARTING);
    }
	if(main.isState(HgState.STARTING)) {
		Random r = new Random();
		Location chest1 = new Location(Bukkit.getWorld("lobbyhg"), 0, 19, 18);
		Chest chestA1 = (Chest) chest1.getBlock().getState();
		Inventory chestMenu = chestA1.getInventory();	
		chestMenu.clear();
		int value = ThreadLocalRandom.current().nextInt(5);
		if(value == 0) {
			chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.STONE_AXE));}
		if(value == 1) {
			chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.APPLE, 3));}
		if(value == 2) {
			chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.CHAINMAIL_LEGGINGS));}
		if(value == 3) {
			chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.IRON_HELMET));}
		if(value == 4) {
			chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.STONE_SWORD));}
	    }
	}
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(main.getPlayers().contains(p)) {
			main.getPlayers().remove(p);
		}
	}
	public void onDamage(EntityDamageByEntityEvent e) {
		Player p2 = (Player) e.getEntity();
		if(main.isState(HgState.PLAYING)) {
			if(main.getPlayers().contains(p2)) {
				e.setCancelled(true);
			}
			if(p2.getHealthScale() <= 0) {
				main.getPlayers().remove(p2);
				specs.add(p2);		
				if(specs.contains(p2)) {
					p2.setGameMode(GameMode.SPECTATOR);					
				}
			}
		}		
		else return;
	}
}
