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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.captainbern.torchofnotch.utils.FireworkEffectPlayer;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FireWorksEffect implements Effect{

	static int randnum;

	@Override
	public void onTorchStrikeEvent(Player player) {
		FireworkEffectPlayer fwp = new FireworkEffectPlayer();
		Block target = player.getTargetBlock(null, 100);
		Location loc = new Location(player.getWorld(), target.getX(), target.getY() + 1, target.getZ());
		try {
			fwp.playFirework(player.getWorld(), loc, getEffect());
		} catch (Exception e) {
			player.sendMessage(ChatColor.RED + "A wild error appeared!");
			e.printStackTrace();
		}
	}
	static List<Color> Colors = Arrays.asList(new Color[] { Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE, Color.YELLOW });
	public static FireworkEffect getEffect(){
		Random generator = new Random();
		randnum = generator.nextInt(17);
		Color chosencolor = (Color)Colors.get(randnum);
		return FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(chosencolor).build();
	}
	@Override
	public void onTorchStrikeEvent(Player player, Entity entity) {
	}
}

