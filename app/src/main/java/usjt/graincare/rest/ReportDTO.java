package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class ReportDTO {

    @SerializedName("date")
    private Calendar initialDate;

    @SerializedName("date")
    private Calendar finalDate;

    public ReportDTO(Calendar initialDate, Calendar finalDate)
    {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Calendar getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Calendar initialDate) {
        this.initialDate = initialDate;
    }

    public Calendar getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Calendar finalDate) {
        this.finalDate = finalDate;
    }
}
