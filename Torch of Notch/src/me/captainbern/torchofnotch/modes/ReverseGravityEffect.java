package me.captainbern.torchofnotch.modes;

import java.util.List;

import me.captainbern.torchofnotch.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ReverseGravityEffect implements Effect{

	private boolean state = false;

	@Override
	public void onTorchStrikeEvent(final Player player) {
		if(state == true){
			state = false;
			player.sendMessage(ChatColor.RED+ "Reversed Gravity off");
		}else{
			state = true;
			player.sendMessage(ChatColor.GREEN + "Reversed Gravity on");
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable(){
				@Override
				public void run() {
					List<Entity> entities = player.getWorld().getEntities();
					for(Entity ent : entities){
						if(ent.getLocation().getChunk().isLoaded()){
							if(ent instanceof Player){
								Player player = (Player) ent;
								if(ent.equals(player)){
									continue;
								}
							}
							ent.setVelocity(new Vector(0, 1, 0));
						}
					}
				}
			}, 1, 1L);
		}
	}

	@Override
	public void onTorchStrikeEvent(Player player, Entity entity) {

	}

}
