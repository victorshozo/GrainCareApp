package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

import usjt.graincare.models.SiloHistory;

public class ReportDTO {

    @SerializedName("startDate")
    private Calendar startDate;
    @SerializedName("endDate")
    private Calendar endDate;
    @SerializedName("siloId")
    private Long siloId;
    @SerializedName("farmName")
    private String farmName;
    //Silos Histories ou sei lá
    private SiloHistory siloHistory;

    @SerializedName("totalTemperatureAverage")
    private Double totalTemperatureAverage;
    @SerializedName("totalAverageHumidity")
    private Double totalAverageHumidity;
    @SerializedName("totalPercentUsed")
    private Double totalPercentUsed;
    @SerializedName("totalKilos")
    private Double totalKilos;
    @SerializedName("profit")
    private Double profit;

    public ReportDTO(Calendar startDate, Calendar endDate, Long siloId, String farmName, SiloHistory siloHistory, Double totalTemperatureAverage, Double totalAverageHumidity, Double totalPercentUsed, Double totalKilos, Double profit) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.siloId = siloId;
        this.farmName = farmName;
        this.siloHistory = siloHistory;
        this.totalTemperatureAverage = totalTemperatureAverage;
        this.totalAverageHumidity = totalAverageHumidity;
        this.totalPercentUsed = totalPercentUsed;
        this.totalKilos = totalKilos;
        this.profit = profit;
    }

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

    public SiloHistory getSiloHistory() {
        return siloHistory;
    }

    public void setSiloHistory(SiloHistory siloHistory) {
        this.siloHistory = siloHistory;
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
}
