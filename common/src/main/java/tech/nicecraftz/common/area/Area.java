package tech.nicecraftz.common.area;

import tech.nicecraftz.common.flag.Flag;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.UUID;

public sealed interface Area permits AreaImpl {
    Map<Flag<?>, Object> getFlags();

    <T> T getFlag(Flag<T> flag);

    <T> void setFlag(Flag<T> flag, T value);

    void removeFlag(String flagName);

    UUID getWorldUUID();

    String getName();

    BoundingBox getBoundingBox();

    class Builder {
        private UUID worldUUID;
        private String name;
        private BoundingBox boundingBox;
        private boolean isEnabled = true;
        private AreaPriority priority = AreaPriority.NULL;

        public Builder setWorldUUID(UUID worldUUID) {
            this.worldUUID = worldUUID;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBoundingBox(BoundingBox boundingBox) {
            this.boundingBox = boundingBox;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.isEnabled = enabled;
            return this;
        }

        public Builder setPriority(AreaPriority areaPriority) {
            this.priority = areaPriority;
            return this;
        }

        public Area build() throws InvalidParameterException {
            if(worldUUID == null ||
                name == null ||
                name.isEmpty() ||
                boundingBox == null
            ) {
                throw new InvalidParameterException("All params of the Area must be set in order to build it!");
            }

            if(priority == AreaPriority.NULL) {
                throw new InvalidParameterException("Area priority must be between 0 and 4 in order to build it!");
            }

            return new AreaImpl(worldUUID, name, boundingBox, isEnabled, priority);
        }
    }
}
