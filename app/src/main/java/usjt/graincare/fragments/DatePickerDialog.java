package usjt.graincare.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

import usjt.graincare.util.DateSettings;

public class DatePickerDialog extends DialogFragment{

    public Dialog onCreateDialog(Bundle savedInstanceState){
        DateSettings dateSettings = new DateSettings(getActivity());

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        android.app.DatePickerDialog dialog;
        dialog = new android.app.DatePickerDialog(getActivity(), dateSettings, year, month, day);
        return  dialog;

    }
}
