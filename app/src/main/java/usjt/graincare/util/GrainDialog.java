package usjt.graincare.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import usjt.graincare.R;

public class GrainDialog {

    public static void showDialog(Context context, String title, String message) {
        try {
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_information_green_64x64)
                    .setMessage(message)
                    .setTitle(title)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } catch (Exception e) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
