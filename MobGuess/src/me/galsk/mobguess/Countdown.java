package me.galsk.mobguess;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.netty.util.internal.ThreadLocalRandom;

public class Countdown implements Runnable {
	private Random r;
	private Main plugin;
	private Player p;
	private boolean guessed = false;
	private int guess;
	private int time;
	private int answer = ThreadLocalRandom.current().nextInt(1, 3);

	public Countdown(Main plugin, Player player) {
		this.plugin = plugin;
		this.p = player;
	}

	private Integer assignedTaskId;

	@Override
	public void run() {
		if (!guessed) {

			Location guesspos1 = (Location) plugin.getCustomConfig().get("guesspos1");
			Location guesspos2 = (Location) plugin.getCustomConfig().get("guesspos2");

			if (p.getLocation().distance(guesspos1) < 1) {
				if (time >= 6) {
					p.sendMessage("You're standing on option 1!");
					guess = 1;
					time = 0;
					guessed = true;
				}
				time += 1;
			}
			if (p.getLocation().distance(guesspos2) < 1) {
				if (time >= 6) {
					p.sendMessage("You're standing on option 2!");
					guess = 2;
					time = 0;
					guessed = true;
				}
				time += 1;
			}
		}
		else {
			if (answer == 1) {
				Location mobpos1 = (Location) plugin.getCustomConfig().get("mobpos1");
				mobpos1.getWorld().spawnEntity(mobpos1, EntityType.SKELETON);

			}
			if (answer == 2) {
				Location mobpos2 = (Location) plugin.getCustomConfig().get("mobpos2");
				mobpos2.getWorld().spawnEntity(mobpos2, EntityType.SKELETON);

			}
			if (guess == answer) {
				p.sendMessage("You win!");
			}
			guessed = false;
			answer = ThreadLocalRandom.current().nextInt(1, 3);

		}

	}

	public void cancelTimer() {
		Bukkit.getScheduler().cancelTask(assignedTaskId);
	}

	public void scheduleTimer() {
		this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 10L);
	}
}
