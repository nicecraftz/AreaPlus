package tech.nicecraftz.common.area;

import lombok.Getter;
import lombok.Setter;
import tech.nicecraftz.common.flag.Flag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
final class AreaImpl implements Area {
    private final UUID worldUUID;
    private final String name;
    private final BoundingBox boundingBox;
    private final Map<Flag<?>, Object> flags = new HashMap<>();
    @Setter
    private boolean isEnabled;
    @Setter
    public AreaPriority priority;

    /*
        creates new Area object with some default parameters such as: isEnabled and priority
     */
    AreaImpl(UUID worldUUID, String name, BoundingBox boundingBox) {
        this.worldUUID = worldUUID;
        this.name = name;
        this.boundingBox = boundingBox;
        this.isEnabled = true;
        this.priority = AreaPriority.LOWEST;
    }

    /*
        creates new Area object with all parameters specified by its Builder
     */
    AreaImpl(UUID worldUUID, String name, BoundingBox boundingBox, boolean isEnabled, AreaPriority priority) {
        this.worldUUID = worldUUID;
        this.name = name;
        this.boundingBox = boundingBox;
        this.isEnabled = isEnabled;
        this.priority = priority;
    }

    @Override
    public Map<Flag<?>, Object> getFlags() {
        return flags;
    }

    @Override
    public <T> T getFlag(Flag<T> flag) {
        return (T) flags.get(flag);
    }

    @Override
    public <T> void setFlag(Flag<T> flag, T value) {
        flags.put(flag, value);
    }

    @Override
    public void removeFlag(String flagName) {
        for(Flag<?> iFlag : getFlags().keySet()) {
            if(iFlag.name().equalsIgnoreCase(flagName)) {
                flags.remove(iFlag);
                break;
            }
        }
    }

}
