package fr.macsouuu.hg.start;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.macsouuu.hg.HgState;
import fr.macsouuu.hg.Main;

public class GameCycle extends BukkitRunnable{
	
	private Main main;
	private int timer = 5;
	private int timer2 = 300;
	
	public GameCycle(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		if(timer == 0) {
			cancel();
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eLe PvP sera actif dans 5s.");
				main.setState(HgState.PVP);
			}
		}
		if(timer2 == 150 || timer2 == 75 || timer2 == 30 || timer2 == 10) {
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eTéléportation finale dans "+timer2+ "s.");
			}
		}
		if(timer2 == 5 || timer2 ==4 || timer2 ==3 || timer2 ==2 || timer2 ==1) {
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eTéléportation finale dans "+timer2);
			}
		}
		if(timer2 == 0) {
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eTéléportation finale !");
			}
			cancel();
		}		
		
		timer2--;
		timer--;
	}
}
