package usjt.graincare.service;

import usjt.graincare.models.ReportDTO;

public interface ReportCallback {
    void success(ReportDTO body);

    void isEmpty();

    void invalidData();

    void error();
}
