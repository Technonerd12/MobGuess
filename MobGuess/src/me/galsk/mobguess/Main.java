package me.galsk.mobguess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.galsk.mobguess.commands.GameCommand;

public class Main extends JavaPlugin {
	public ArrayList<MobGuessPlayer> players = new ArrayList<>();

	private File customConfigFile;
	private FileConfiguration customConfig;

	private void registerComannds() {
		this.getCommand("game").setExecutor(new GameCommand(this));
	}

	private void registerListeners() {

	}

	public FileConfiguration getCustomConfig() {
		return this.customConfig;
	}

	public void reloadCustomConfig() {
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	}

	private void createCustomConfig() {
		customConfigFile = new File(getDataFolder(), "locations.yml");
		if (!customConfigFile.exists()) {
			customConfigFile.getParentFile().mkdirs();
			saveResource("locations.yml", false);
		}

		customConfig = new YamlConfiguration();
		try {
			customConfig.load(customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void saveCustomConfig() {
		try {
			this.getCustomConfig().save(customConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEnable() {
		createCustomConfig();
		this.saveDefaultConfig();
		registerComannds();
		registerListeners();

	}

	@Override
	public void onDisable() {

	}

}
