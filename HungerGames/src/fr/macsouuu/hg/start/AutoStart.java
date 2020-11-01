package fr.macsouuu.hg.start;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.macsouuu.hg.HgState;
import fr.macsouuu.hg.Main;

public class AutoStart extends BukkitRunnable{
	
	private int timer = 30;
	private Main main;

	public AutoStart(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		for(Player p : main.getPlayers()) {
			p.sendMessage("rtyjtyukyukyuk");
			p.setLevel(timer);
		}
		if(timer == 15 || timer == 10 || timer == 5 || timer ==4 || timer ==3 || timer ==2 || timer ==1) {
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eTéléportation dans "+timer);
			}
		}
		if(timer == 0) {
			for(Player p : main.getPlayers()) {
				p.sendMessage("§eTéléportation !");
			}
			main.setState(HgState.PLAYING);
			for(int i = 0; i < main.getPlayers().size(); i++) {
				Player player = main.getPlayers().get(i);
				Location spawn = main.getSpawns().get(i);
				player.teleport(spawn);
				player.getInventory().clear();
				player.setGameMode(GameMode.SURVIVAL);				
			}
			GameCycle cycle = new GameCycle(main);
			cycle.runTaskTimer(main, 0, 20);			
			cancel();
		}
		timer--;
	}

}
