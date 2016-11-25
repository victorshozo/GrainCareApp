package usjt.graincare.models;

import java.util.List;

public class SiloHistory {

    private Long id;
    private Grao grao;
    private List<SensorHistory> sensorsHistory;
    private String openedAt;
    private String closedAt;
    private Boolean open;
    private Silo silo;

    @Deprecated
    SiloHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grao getGrao() {
        return grao;
    }

    public void setGrao(Grao grao) {
        this.grao = grao;
    }

    public List<SensorHistory> getsensorsHistory() {
        return sensorsHistory;
    }

    public void setsensorsHistory(List<SensorHistory> sensorsHistory) {
        this.sensorsHistory = sensorsHistory;
    }

    public String getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Silo getSilo() {
        return silo;
    }

    public void setSilo(Silo silo) {
        this.silo = silo;
    }
}