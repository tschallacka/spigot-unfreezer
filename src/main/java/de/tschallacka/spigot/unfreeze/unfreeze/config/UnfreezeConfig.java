package de.tschallacka.spigot.unfreeze.unfreeze.config;


import java.util.ArrayList;
import java.util.List;

import de.tschallacka.spigot.unfreeze.unfreeze.Unfreeze;
import org.bukkit.configuration.file.FileConfiguration;

public final class UnfreezeConfig extends Config {

    private static UnfreezeConfig instance;



    private static int unfreezeCheckTicks;
    private static int checkRange;

    private UnfreezeConfig()
    {
        super("unfreeze_config.yml");
    }


    @Override
    public void load() {
        unfreezeCheckTicks = getConfig().getInt("unfreeze-check-every-ticks", 200);
        if(unfreezeCheckTicks < 1) {
            unfreezeCheckTicks = 1;
            Unfreeze.log().info("Frequency was set to invalid value, setting to default 200 ticks");
        }
        Unfreeze.log().info("Setting checking frequency to: " + unfreezeCheckTicks + " ticks");
        getConfig().set("unfreeze-check-every-ticks", unfreezeCheckTicks);

        checkRange = getConfig().getInt("check-radius-in-blocks", 50);
        if(checkRange < 1) {
            checkRange = 1;
            Unfreeze.log().info("Range was set to invalid value, setting to default 50 blocks");
        }
        Unfreeze.plugin.getLogger().info("Setting checking radius to: " + checkRange + " blocks");
        getConfig().set("check-radius-in-blocks", checkRange);
    }

    public static FileConfiguration get()
    {
        return getInstance().config;
    }

    public static UnfreezeConfig getInstance()
    {
        if (instance == null) {
            instance = new UnfreezeConfig();
        }
        return instance;
    }

    @Override
    public void update()
    {
        return;
    }

    public static int getUnfreezeCheckTicks()
    {
        return unfreezeCheckTicks;
    }

    public static int getCheckRange()
    {
        return checkRange;
    }

}