package tech.nicecraftz.common.flag;

public interface Flag<T> {

    String name();

    T test();

    T defaultValue();
}
