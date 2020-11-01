package fr.macsouuu.hg.listerners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.macsouuu.hg.Main;

public class Listerners implements Listener {
	private Main main;
	public Listerners(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory i = e.getClickedInventory();
		ItemStack it = e.getCurrentItem();
		Inventory inv = Bukkit.createInventory(null, 27, "§7Joueurs");
		if (i != null) {
			if(it == null)return;
		if(i.getName().equalsIgnoreCase("§7Menu")) {
			if(it.getType() == Material.RED_MUSHROOM && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§6HungerGames")) {
				p.performCommand("menu hgjoin");
				e.setCancelled(true);
				p.closeInventory();
			}
			if(it.getType() == Material.STAINED_GLASS_PANE && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§7 ")) {
				e.setCancelled(true);
		    	}
	    	}
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§7Joueurs")) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
	   	    SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setOwner(main.getPlayers().toString());
			sm.setDisplayName("§7"+main.getPlayers().toString());
	   	    skull.setItemMeta(sm);
			p.openInventory(inv);
			if(i.getName().equalsIgnoreCase("§7")) {
				if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().startsWith("§7")) {					
				}
			    }
	    	}  	    	
	    }
	}
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Item droppedItem = e.getItemDrop();
		ItemStack DroppedItemStack = droppedItem.getItemStack();
		if(DroppedItemStack.getType() == Material.RED_MUSHROOM && DroppedItemStack.hasItemMeta() && DroppedItemStack.getItemMeta().hasDisplayName() && DroppedItemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6HungerGames")){			
			e.setCancelled(true);
		}
		if(DroppedItemStack.getType() == Material.STAINED_GLASS_PANE && DroppedItemStack.hasItemMeta() && DroppedItemStack.getItemMeta().hasDisplayName() && DroppedItemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§7 ")){
			e.setCancelled(true);
		}
	}
}
