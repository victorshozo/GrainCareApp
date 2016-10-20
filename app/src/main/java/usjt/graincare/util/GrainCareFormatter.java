package usjt.graincare.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GrainCareFormatter {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static String from(Calendar predictionDate) {
        return sdf.format(predictionDate.getTime());
    }
}
