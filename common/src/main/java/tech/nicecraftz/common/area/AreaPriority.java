package tech.nicecraftz.common.area;

public enum AreaPriority {
    NULL(-1), LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4);

    public final int value;

    AreaPriority(int priority) {
        this.value = priority;
    }

    public boolean isStrictlyGreaterThan(AreaPriority end) {
        return this.value > end.value;
    }

    public static boolean isStrictlyGreaterThan(AreaPriority start, AreaPriority end) {
        return start.value > end.value;
    }
}
