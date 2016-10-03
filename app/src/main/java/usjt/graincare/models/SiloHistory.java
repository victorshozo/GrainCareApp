package usjt.graincare.models;

import java.util.Calendar;
import java.util.List;

public class SiloHistory {
    private Long id;
    private Grao grao;
    private List<BeaconHistory> beacons;
    private Calendar openedAt;
    private Calendar closedAt;
    private Boolean open;
    private Silo silo;

    public SiloHistory(Long id, Grao grao, List<BeaconHistory> beacons, Calendar openedAt, Calendar closedAt, Boolean open, Silo silo){

        this.id = id;
        this.grao = grao;
        this.beacons = beacons;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.open = open;
        this.silo = silo;
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

    public List<BeaconHistory> getBeacons() {
        return beacons;
    }

    public Calendar getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(Calendar openedAt) {
        this.openedAt = openedAt;
    }

    public Calendar getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Calendar closedAt) {
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
}
