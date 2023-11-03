package tech.nicecraftz.spigot.manager;

import com.google.common.collect.Maps;
import org.bukkit.Location;
import tech.nicecraftz.common.area.Area;
import tech.nicecraftz.common.area.BoundingBox;
import tech.nicecraftz.common.area.Point;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AreaManager {
    private final Map<String, Area> areas = Maps.newHashMap();

    public Optional<Area> get(String name) {
        return Optional.ofNullable(areas.get(name.toLowerCase()));
    }

    public Optional<Area> get(Location location) {
        UUID worldUUID = location.getWorld().getUID();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        for (Area area : areas.values()) {
            if (!area.getWorldUUID().equals(worldUUID)) continue;
            if (area.getBoundingBox().isInside(new Point(x, y, z))) return Optional.of(area);
        }

        return Optional.empty();
    }

    public void create(String name, Location loc1, Location loc2) {
        Point point1 = new Point(loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ());
        Point point2 = new Point(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
        BoundingBox boundingBox = new BoundingBox(point1, point2);
        Area area = new Area(loc1.getWorld().getUID(), "test", boundingBox);
        areas.put(name.toLowerCase(), area);
    }

    public void delete(String name) {
        areas.remove(name.toLowerCase());
    }
}
