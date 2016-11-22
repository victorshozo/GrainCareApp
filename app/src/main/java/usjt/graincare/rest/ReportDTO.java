package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

import usjt.graincare.models.SiloHistory;

public class ReportDTO {

    @SerializedName("reportStart")
    private Calendar startDate;
    @SerializedName("reportEnd")
    private Calendar endDate;
    @SerializedName("siloId")
    private Long siloId;
    @SerializedName("farmName")
    private String farmName;
    @SerializedName("data")
    private Integer[] data;
    @SerializedName("totalAverageTemperature")
    private Double totalTemperatureAverage;
    @SerializedName("totalAverageHumidity")
    private Double totalAverageHumidity;
    @SerializedName("totalCapacityUsed")
    private Double totalPercentUsed;
    @SerializedName("totalKilos")
    private Double totalKilos;
    @SerializedName("totalProfit")
    private Double profit;
    @SerializedName("totalWeight")
    private Double totalWeight;

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Long getSiloId() {
        return siloId;
    }

    public void setSiloId(Long siloId) {
        this.siloId = siloId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Double getTotalTemperatureAverage() {
        return totalTemperatureAverage;
    }

    public void setTotalTemperatureAverage(Double totalTemperatureAverage) {
        this.totalTemperatureAverage = totalTemperatureAverage;
    }

    public Double getTotalAverageHumidity() {
        return totalAverageHumidity;
    }

    public void setTotalAverageHumidity(Double totalAverageHumidity) {
        this.totalAverageHumidity = totalAverageHumidity;
    }

    public Double getTotalPercentUsed() {
        return totalPercentUsed;
    }

    public void setTotalPercentUsed(Double totalPercentUsed) {
        this.totalPercentUsed = totalPercentUsed;
    }

    public Double getTotalKilos() {
        return totalKilos;
    }

    public void setTotalKilos(Double totalKilos) {
        this.totalKilos = totalKilos;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getDataSize() {return data.length;}
}
