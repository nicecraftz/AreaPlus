package tech.nicecraftz.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import tech.nicecraftz.common.AreaPlusAPI;
import tech.nicecraftz.spigot.manager.AreaManager;

public class AreaPlus extends JavaPlugin {
    private AreaManager areaManager;


    @Override
    public void onEnable() {
        new AreaPlusAPI();
        areaManager = new AreaManager();


    }
}


