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

package me.captainbern.torchofnotch;

/**
 * @author CaptainBern
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.captainbern.torchofnotch.command.CommandManager;
import me.captainbern.torchofnotch.command.commands.HelpCommand;
import me.captainbern.torchofnotch.command.commands.ToggleHandler;
import me.captainbern.torchofnotch.listener.ToggleListener;
import me.captainbern.torchofnotch.pluginutils.Metrics;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static FileConfiguration config;
	public static File configfile;

	public static HashMap<String, Mode> mode = new HashMap<String, Mode>();
	public static List<String> players = new ArrayList<String>();
	public static Plugin plugin;
	public static Main main;

	public static ItemStack torch;

	public void onDisable(){
		getLogger().info("Disabled!");
	}

	public Main(){

		main = this;
	}

	public void onEnable(){

		plugin = this;
		
		//command registering
		CommandManager cmdman = new CommandManager();
		cmdman.registerNewCommand("help", new HelpCommand());
		cmdman.registerNewCommand("toggle", new ToggleHandler());
		getCommand("notch").setExecutor(new CommandManager());

		configfile = new File(getDataFolder(), "config.yml");

		try{
			firstRun();
		}catch(Exception e){
			e.printStackTrace();
		}

		config = new YamlConfiguration();
		loadYamls();

		torch = new ItemStack(Material.getMaterial(config.getInt("item")));

		PluginManager manager = Bukkit.getServer().getPluginManager();

		manager.registerEvents(new ToggleListener(), this);

		Player[] players = Bukkit.getOnlinePlayers();
		for(Player player : players){
			if(players.length > 0){
				if(player.hasPermission("torch.use")){
					mode.put(player.getName(), Mode.Lightning);
				}
			}
		}
		
		getLogger().info("Enabled!");
		
	}

	private void loadYamls() {
		try {
			config.load(configfile);
			if(config.getBoolean("metrics")){
				Metrics metrics = new Metrics(this);
				metrics.start();
				getLogger().info("Metrics enabled!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void firstRun() {
		if(!configfile.exists()){ //make the config
			configfile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configfile);
		}
	}

	private static void copy(InputStream in, File file){
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Mode getType(Player player){
		return mode.get(player.getName());
	}
	
}
