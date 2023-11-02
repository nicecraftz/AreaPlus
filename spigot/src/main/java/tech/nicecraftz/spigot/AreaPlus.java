package tech.nicecraftz.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import tech.nicecraftz.common.AreaPlusAPI;

public class AreaPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        new AreaPlusAPI();

    }
}


