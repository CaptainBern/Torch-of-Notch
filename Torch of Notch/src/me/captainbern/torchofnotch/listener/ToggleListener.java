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

package me.captainbern.torchofnotch.listener;

/**
 * @author CaptainBern
 */

import me.captainbern.torchofnotch.Main;
import me.captainbern.torchofnotch.Mode;
import me.captainbern.torchofnotch.logger.Notifier;
import me.captainbern.torchofnotch.modes.Effect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToggleListener implements Listener {

	Notifier n = new Notifier();

	@EventHandler
	public void onToggle(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(player.getItemInHand().equals(Main.torch)){
			if(player.hasPermission("torch.use")){
				if(Main.players.contains(player.getName())){
					if((event.getAction().equals(Action.LEFT_CLICK_AIR)) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))){
						if(Main.mode.containsKey(player.getName())){

							Mode mode = Main.getType(player);

							int typeindex = Main.getType(player).ordinal();

							if(typeindex == Mode.values().length - 1){
								typeindex = 0;
							}else{
								typeindex++;
							}

							Main.mode.put(player.getName(), Mode.values()[typeindex]);
							n.sendMode(player, mode);

						}else{
							Main.mode.put(player.getName(), Mode.Lightning);
							player.sendMessage(ChatColor.BLUE + "Mode: " + Main.getType(player));
						}
					}else if((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
						String modeclass = "me.captainbern.torchofnotch.modes." + Main.getType(player).toString() + "Effect";	
						Effect effect = null;
						try {
							effect = (Effect) Class.forName(modeclass).newInstance();
							effect.onTorchStrikeEvent(player);
						} catch (InstantiationException e) {
							Main.plugin.getLogger().warning("Could not instantiate class " + modeclass);
							player.sendMessage(ChatColor.RED + "Could not instantiate class " + modeclass);
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							Main.plugin.getLogger().warning("Could not access class " + modeclass);
							player.sendMessage(ChatColor.RED + "Could not access class " + modeclass);
							e.printStackTrace();
						}catch(ClassNotFoundException e){
							Main.plugin.getLogger().warning("Could not find class " + modeclass);
							player.sendMessage(ChatColor.RED + "Could not find class " + modeclass);
							e.printStackTrace();
						}catch(Exception e){
							Main.plugin.getLogger().warning("A wild error occurred");
							player.sendMessage(ChatColor.RED + "A wild error occurred!");
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/*
	 * Experimental code
	 */
	/*public void onInteract(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if(player.getItemInHand().equals(Main.torch)){
			if(player.hasPermission("torch.use")){
				String modeclass = "me.captainbern.torchofnotch.modes." + Main.getType(player).toString() + "Effect";	
				Effect effect = null;
				try {
					effect = (Effect) Class.forName(modeclass).newInstance();
					effect.onTorchStrikeEvent(player, entity);
				} catch (InstantiationException e) {
					Main.plugin.getLogger().warning("Could not instantiate class " + modeclass);
					player.sendMessage(ChatColor.RED + "Could not instantiate class " + modeclass);
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					Main.plugin.getLogger().warning("Could not access class " + modeclass);
					player.sendMessage(ChatColor.RED + "Could not access class " + modeclass);
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					Main.plugin.getLogger().warning("Could not find class " + modeclass);
					player.sendMessage(ChatColor.RED + "Could not find class " + modeclass);
					e.printStackTrace();
				}catch(Exception e){
					Main.plugin.getLogger().warning("A wild error occurred");
					player.sendMessage(ChatColor.RED + "A wild error occurred!");
					e.printStackTrace();
				}
			}
		}
	}*/
}

