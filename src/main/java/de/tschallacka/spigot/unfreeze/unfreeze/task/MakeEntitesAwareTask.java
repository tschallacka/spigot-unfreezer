package de.tschallacka.spigot.unfreeze.unfreeze.task;

import de.tschallacka.spigot.unfreeze.unfreeze.config.UnfreezeConfig;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class MakeEntitesAwareTask extends BukkitRunnable {

    @Override
    public void run()
    {
        for(World world : Bukkit.getWorlds()) {
            checkWorld(world.getName());
        }
    }

    /**
     * Fetches all players within the world, then fetches all entities within tracking range
     * Filters out those who are "aware" of their existence,
     * forces awareness upon those unaware
     * @param worldName
     */
    public void checkWorld(String worldName)
    {
        int trackingRange = UnfreezeConfig.getCheckRange();
        List<Player> players;
        try {
            players = Bukkit.getWorld(worldName).getPlayers();
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            return;
        }
        if(players.isEmpty()) return;

        players.stream()
                .flatMap(player -> player.getNearbyEntities(trackingRange, trackingRange, trackingRange).stream())
                .map(entity -> ((CraftEntity)entity).getHandle())
                .filter(entity -> entity instanceof EntityInsentient && !(((EntityInsentient)entity).aware) )
                .forEach(entity -> ((EntityInsentient)entity).aware = true);


    }

}