package usjt.graincare.application;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import usjt.graincare.R;
import usjt.graincare.application.util.Font;

public class GrainCareSnackBar {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void show(View rootView, String text, int time) {
        Snackbar snackbar = Snackbar.make(rootView, text, time);
        snackbar.getView().setBackground(rootView.getResources().getDrawable(R.color.white));
        TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTypeface(Font.museoSans(rootView.getContext()));
        tv.setTextColor(rootView.getResources().getColor(R.color.colorPrimaryDark));
        tv.setTextSize(18f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar.show();
    }
}