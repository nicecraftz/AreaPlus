package tech.nicecraftz.common.area;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tech.nicecraftz.common.flag.Flag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Area {
    private final UUID worldUUID;
    private final String name;
    private final BoundingBox boundingBox;
    private final Map<Flag<?>, Object> flags = new HashMap<>();

    public <T> T getFlag(Flag<T> flag) {
        return (T) flags.get(flag);
    }

    public <T> void setFlag(Flag<T> flag, T value) {
        flags.put(flag, value);
    }
}
