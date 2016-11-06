package usjt.graincare.service;

public interface ReportCallback {
    void success();

    void invalidData();

    void error();
}
