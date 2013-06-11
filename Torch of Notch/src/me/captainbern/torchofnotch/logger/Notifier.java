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

package me.captainbern.torchofnotch.logger;

/**
 * @author CaptainBern
 */

import me.captainbern.torchofnotch.Main;
import me.captainbern.torchofnotch.Mode;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Notifier {
	/*
	 * The "Mode" arg isn't used here, dunno why I left it tough...
	 */
	public void sendMode(Player player, Mode mode){
		player.sendMessage(ChatColor.BLUE + "Mode: " + Main.getType(player));
	}

}
