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

package me.captainbern.torchofnotch.command;

/**
 * @author CaptainBern
 */

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

	private static HashMap<String, TorchCommand> registry = new HashMap<String, TorchCommand>();

	public void registerNewCommand(String name, TorchCommand cmd){
		registry.put(name, cmd);
	}

	public boolean exists(String name){
		return registry.containsKey(name);
	}

	public TorchCommand getExecutor(String name){
		return registry.get(name);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		//help command
		if(args.length == 0 || args[0].equals("help")){
			getExecutor("help").onCommand(sender, cmd, label, args);
			return true;
		}
		//other commands
		if(exists(args[0])){
			getExecutor(args[0]).onCommand(sender, cmd, label, args);
			return true;
		}else{
			sender.sendMessage(ChatColor.RED + "This command does not exist! Try /notch help to see a list of commands!");
		}
		return false;
	}
}
