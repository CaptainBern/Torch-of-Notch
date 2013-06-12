/*
 * Torch of Notch
 * Copyright (C) 2012 CaptainBern <http://dev.bukkit.org/bukkit-mods/torch-notch/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package me.captainbern.torchofnotch.modes;

/**
 * @author CaptainBern
 */

import java.util.List;

import me.captainbern.torchofnotch.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ReverseGravityEffect implements Effect{

	private boolean state;

	@Override
	public void onTorchStrikeEvent(final Player player) {
		if(state == true){
			player.sendMessage(ChatColor.RED+ "Reversed Gravity off");
			Bukkit.getScheduler().cancelTasks(Main.plugin);
			state = false;
		}else{
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
			state = true;
		}
	}

	@Override
	public void onTorchStrikeEvent(Player player, Entity entity) {

	}

}
