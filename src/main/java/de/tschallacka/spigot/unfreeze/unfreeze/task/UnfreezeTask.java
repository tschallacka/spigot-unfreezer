package de.tschallacka.spigot.unfreeze.unfreeze.task;

import de.tschallacka.spigot.unfreeze.unfreeze.config.UnfreezeConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Simple task handler for starting and stopping the given task.
 */
public class UnfreezeTask
{
    BukkitTask task;

    public BukkitTask CheckForFrozenEntities(Plugin plugin)
    {
        int durationInTicks = UnfreezeConfig.getUnfreezeCheckTicks();
        this.task = new MakeEntitesAwareTask().runTaskTimer(plugin, durationInTicks, durationInTicks);
        return this.task;
    }

    public void stopTask()
    {
        Bukkit.getScheduler().cancelTask(this.task.getTaskId());
    }
}

