package tech.nicecraftz.spigot.manager;

import com.google.common.collect.Sets;
import org.bukkit.Location;
import tech.nicecraftz.common.area.Area;
import tech.nicecraftz.common.area.AreaPriority;
import tech.nicecraftz.common.location.BoundingBox;
import tech.nicecraftz.common.location.Point;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class AreaManager {
    private final Set<Area> loadedAreas;

    public AreaManager() {
         loadedAreas = Sets.newHashSet();
    }

    public Optional<Area> getByName(String name) {
        for(Area area : loadedAreas) {
            if(area.getName().equalsIgnoreCase(name)) {
                return Optional.of(area);
            }
        }

        return Optional.empty();
    }

    public Optional<Area> getByLocation(Location location) {
        if(location == null || location.getWorld() == null) {
            return Optional.empty();
        }

        UUID worldUUID = location.getWorld().getUID();
        Point point = new Point(location.getBlockX(), location.getBlockY(), location.getBlockZ());

        for (Area area : loadedAreas) {
            if (area.getWorldUUID().equals(worldUUID) && area.getBoundingBox().containsPoint(point)) {
                return Optional.of(area);
            }
        }

        return Optional.empty();
    }

    public Optional<Area> createArea(String name, Location startLoc, Location endLoc) {
        if(startLoc == null || startLoc.getWorld() == null || endLoc == null || endLoc.getWorld() == null) {
            return Optional.empty();
        }

        UUID startWUUID = startLoc.getWorld().getUID();

        if(!startWUUID.equals(endLoc.getWorld().getUID())) {
            return Optional.empty();
        }

        Point startPoint = new Point(startLoc.getBlockX(), startLoc.getBlockY(), startLoc.getBlockZ()),
            endPoint = new Point(endLoc.getBlockX(), endLoc.getBlockY(), endLoc.getBlockZ());

        Area area = new Area.Builder()
            .setWorldUUID(startWUUID)
            .setName(name.toLowerCase())
            .setBoundingBox(new BoundingBox(startPoint, endPoint))
            .setEnabled(true)
            .setPriority(AreaPriority.HIGHEST)
            .build();

        loadedAreas.add(area);

        return Optional.of(area);
    }

    public void removeByName(String name) {
        for(Area area : Sets.newHashSet(loadedAreas)) {
            if(area.getName().equalsIgnoreCase(name)) {
                loadedAreas.remove(area);
            }
        }
    }
}
