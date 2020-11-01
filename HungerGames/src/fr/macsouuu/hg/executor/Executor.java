package fr.macsouuu.hg.executor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.macsouuu.hg.Main;

public class Executor implements CommandExecutor {
	private Main main;
	public Executor(Main main) {
		this.main = main;
	}
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player p = (Player) s;
		Inventory inv = Bukkit.createInventory(null, 9, "§7Menu");
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte)8);
		ItemMeta glassM = glass.getItemMeta();
		glassM.setDisplayName("§7 ");
		ItemStack arc = new ItemStack(Material.RED_MUSHROOM);
		ItemMeta bow = arc.getItemMeta();
		bow.setDisplayName("§6HungerGames");
		arc.setItemMeta(bow);
		glass.setItemMeta(glassM);
		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, arc);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
				
		if(cmd.getName().equalsIgnoreCase("menu")) {
			if(!p.hasPermission("menu.use")) {
				p.sendMessage("§cVous n'avez pas la permission d'éxécuter cette commande.");
				return true;
			}
		if(args.length == 0) {
			p.sendMessage("§cSyntaxe: /menu <nom du menu>");
			return true;
		}
		if(args.length >= 1) {			
			if(args[0].equalsIgnoreCase("hg")) {
				if(p.hasPermission("menu.use.hg")) {
				p.openInventory(inv);
				}
				else
					p.sendMessage("§cVous n'avez pas la permission d'éxécuter cette commande.");
				return true;
			}
			if(args[0].equalsIgnoreCase("hgjoin")) {
				if(p.hasPermission("menu.use.hg.join")) {
					if(!main.getPlayers().contains(p)) main.getPlayers().add(p);
					for(Player p2 : main.getPlayers()) {
					p2.sendMessage("§c[§6Hunger Games§c]§r§e "+ p.getName()+" viens de rejoindre le jeu ! <"+main.getPlayers().size()+"/20>");
					     }
				     }
				else
					p.sendMessage("§cVous n'avez pas la permission d'éxécuter cette commande.");
				return true;
		    	}
			else
				p.sendMessage("§cNom du menu inconnu.");
	    	}
    	}	
		return false;
	}

}
