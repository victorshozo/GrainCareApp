package usjt.graincare.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import usjt.graincare.R;

public class PrevisionDialog {

    public static void showDialog(Context context, String title, String message) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setButton("OK", (dialog, which) -> {
            });
            alertDialog.setIcon(R.drawable.icon_alert_info);
            alertDialog.show();
        } catch (Exception e) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}