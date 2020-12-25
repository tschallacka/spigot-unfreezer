package de.tschallacka.spigot.unfreeze.unfreeze;

import de.tschallacka.spigot.unfreeze.unfreeze.config.UnfreezeConfig;
import de.tschallacka.spigot.unfreeze.unfreeze.task.UnfreezeTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Unfreeze extends JavaPlugin
{

    public static Unfreeze plugin;
    private UnfreezeTask task;

    @Override
    public void onEnable()
    {
        getLogger().info("Starting Entity Unfreezer task");
        plugin = this;
        UnfreezeConfig.getInstance().setup(plugin);
        task = new UnfreezeTask();
        task.CheckForFrozenEntities(plugin);
    }

    @Override
    public void onDisable()
    {
        this.task.stopTask();
        getLogger().info("Stopped Entity Unfreezer task");
    }

    public void reload()
    {
        this.onDisable();
        this.onEnable();
    }

    public static Logger log()
    {
        return plugin.getLogger();
    }
}
