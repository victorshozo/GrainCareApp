package usjt.graincare.silo;

public interface SiloChangedCallback {

    void success();

    void invalidData();

    void error();
}
