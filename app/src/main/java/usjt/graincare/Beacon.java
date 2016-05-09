package usjt.graincare;
public class Beacon {
    private String beaconID;
    private int beaconBatteryIcon;
    private String beaconBattery;
    private String beaconTemperature;
    private int beaconColor;
    public Beacon (String beaconID, int beaconBatteryIcon,String beaconBattery, String beaconTemperature)
    {
        this.beaconID = beaconID;
        this.beaconBatteryIcon = beaconBatteryIcon;
        this.beaconBattery = beaconBattery;
        this.beaconTemperature = beaconTemperature;
    }

    public String  getBeaconID() {
        return beaconID;
    }

    public String getBeaconBattery() {
        return beaconBattery;
    }

    public String getBeaconTemperature() {
        return beaconTemperature;
    }

    public void setBeaconBatteryIcon(int beaconBatteryIcon) {
        this.beaconBatteryIcon = beaconBatteryIcon;
    }

    public int getBeaconBatteryIcon() {
        return beaconBatteryIcon;
    }
}

