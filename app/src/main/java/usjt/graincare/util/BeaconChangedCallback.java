package usjt.graincare.util;

public interface BeaconChangedCallback {
    void success();

    void invalidData();

    void error();
}