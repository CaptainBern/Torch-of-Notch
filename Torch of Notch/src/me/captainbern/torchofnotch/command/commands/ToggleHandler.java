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

package me.captainbern.torchofnotch.command.commands;

/**
 * @author CaptainBern
 */

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.captainbern.torchofnotch.Main;
import me.captainbern.torchofnotch.command.TorchCommand;

public class ToggleHandler implements TorchCommand {

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(player.hasPermission("torch.use")){
				if(Main.players.contains(player.getName())){
					Main.players.remove(player.getName());
					player.sendMessage(ChatColor.RED + "Torch disabled");
				}else{
					Main.players.add(player.getName());
					player.sendMessage(ChatColor.GREEN + "Torch enabled");
				}
			}return;
		}else{
			sender.sendMessage(ChatColor.RED + "Only for players!");
		}return;
	}

}
