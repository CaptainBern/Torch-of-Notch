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

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportEffect implements Effect{

	@Override
	public void onTorchStrikeEvent(Player player) {
		Block target = player.getTargetBlock(null, 200);
		Location newLoc = target.getLocation();
		Location tempLoc = newLoc;
		while ((!newLoc.getBlock().isEmpty()) && (newLoc.getY() < 127.0D)) {
			newLoc.add(0.0D, 1.0D, 0.0D);
		}

		if (newLoc.getY() - tempLoc.getY() < 3.0D) {
			newLoc.setPitch(0.0F);
			player.teleport(newLoc);
		} else {
			player.sendMessage(ChatColor.RED + "Seems like it is not very safe to stand there!");
		}
	}

	@Override
	public void onTorchStrikeEvent(Player player, Entity entity) {
	}
}


