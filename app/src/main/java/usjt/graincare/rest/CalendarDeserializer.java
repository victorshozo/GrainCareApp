package usjt.graincare.rest;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDeserializer implements JsonDeserializer<Calendar> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Calendar calendar = new GregorianCalendar();
        String jsonDate = json.getAsJsonPrimitive().getAsString();
        try {
            Date date;
            if (jsonDate.contains("/")) {
                date = sdf2.parse(jsonDate);
            } else {
                date = sdf.parse(jsonDate);
            }
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
