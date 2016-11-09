package usjt.graincare.util;

public interface SensorChangedCallback {
    void success();

    void invalidData();

    void error();
}