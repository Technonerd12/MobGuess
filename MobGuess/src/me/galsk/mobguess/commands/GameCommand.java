package me.galsk.mobguess.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.galsk.mobguess.Countdown;
import me.galsk.mobguess.Main;

public class GameCommand implements CommandExecutor {
	private final Main plugin;

	public GameCommand(Main plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("mobpos1")) {
					plugin.getCustomConfig().set("mobpos1", p.getLocation());
					plugin.saveCustomConfig();
				}
				if (args[0].equalsIgnoreCase("mobpos2")) {
					plugin.getCustomConfig().set("mobpos2", p.getLocation());
					plugin.saveCustomConfig();
				}
				if (args[0].equalsIgnoreCase("guesspos1")) {
					plugin.getCustomConfig().set("guesspos1", p.getLocation());
					plugin.saveCustomConfig();
				}
				if (args[0].equalsIgnoreCase("guesspos2")) {
					plugin.getCustomConfig().set("guesspos2", p.getLocation());
					plugin.saveCustomConfig();
				}
				if (args[0].equalsIgnoreCase("start")) {
					Location mobpos1 = (Location) plugin.getCustomConfig().get("mobpos1");
					Location mobpos2 = (Location) plugin.getCustomConfig().get("mobpos2");
					Location guesspos1 = (Location) plugin.getCustomConfig().get("guesspos1");
					Location guesspos2 = (Location) plugin.getCustomConfig().get("guesspos2");
					p.sendMessage("" + mobpos1);
					p.sendMessage("" + mobpos2);
					p.sendMessage("" + guesspos1);
					p.sendMessage("" + guesspos2);
					Countdown game = new Countdown(plugin, p);
					game.scheduleTimer();
					
				}
			}
			

		} else {
			sender.sendMessage(plugin.getConfig().getString("senderNotAPlayer"));
		}

		return false;
	}
}
