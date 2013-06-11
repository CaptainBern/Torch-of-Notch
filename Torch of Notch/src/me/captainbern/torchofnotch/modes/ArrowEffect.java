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

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ArrowEffect implements Effect{

	@Override
	public void onTorchStrikeEvent(Player player) {
		/*
		 * Tried my best to make arrows as accurate as possible...
		 */
		double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
		double yaw  = ((player.getLocation().getYaw() + 90)  * Math.PI) / 180;

		double x = Math.sin(pitch) * Math.cos(yaw);
		double z = Math.sin(pitch) * Math.sin(yaw);
		double y = Math.cos(pitch);

		player.launchProjectile(Arrow.class).setVelocity(new Vector(x, y, z));

	}

	@Override
	public void onTorchStrikeEvent(Player player, Entity entity) {
	}
}