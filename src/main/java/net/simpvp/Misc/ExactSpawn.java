package net.simpvp.Misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Ensures players respawn at the exact spawn location
 */
public class ExactSpawn implements Listener {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled=false)
	public void onPlayerLogin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPlayedBefore()) {
			return;
		}

		Misc.instance.getLogger().info(event.getPlayer().getName()
				+ " joined for first time. Porting to spawn.");

		Location loc = Misc.instance.getServer().getWorld("world").getSpawnLocation();
		loc.setX(loc.getX() + 0.5);
		loc.setZ(loc.getZ() + 0.5);

		event.getPlayer().teleport(loc);

	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled=false)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (event.isBedSpawn() || event.isAnchorSpawn()) {
			return;
		}

		Location loc = Misc.instance.getServer().getWorld("world").getSpawnLocation();
		loc.setX(loc.getX() + 0.5);
		loc.setZ(loc.getZ() + 0.5);

		event.setRespawnLocation(loc);
	}

}

