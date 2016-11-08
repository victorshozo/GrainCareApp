package usjt.graincare.service;

import usjt.graincare.rest.ReportDTO;

public interface ReportCallback {
    void success(ReportDTO body);

    void invalidData();

    void error();
}
