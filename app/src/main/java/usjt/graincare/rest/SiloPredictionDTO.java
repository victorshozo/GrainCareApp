package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class SiloPredictionDTO {

    @SerializedName("date")
    private Calendar predictionDate;

    public Calendar getPredictionDate() {
        return predictionDate;
    }
}
