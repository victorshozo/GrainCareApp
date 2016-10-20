package usjt.graincare.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;

public class PredictionSiloDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Calendar date;

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}