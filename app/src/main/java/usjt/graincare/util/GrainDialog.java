package usjt.graincare.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.core.io.AssetResource;
import org.springframework.core.io.Resource;

import usjt.graincare.R;

public class GrainDialog {

    public static AlertDialog showDialog(Context context, String title, String message) {
        try {
            AlertDialog alert = new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_information_green_64x64)
                    .setMessage(message)
                    .setTitle(title)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();
            TextView tvMessage = (TextView) alert.findViewById(android.R.id.message);
            Typeface face=Typeface.createFromAsset(context.getResources().getAssets(),"fonts/museo-sans.ttf");
            tvMessage.setTypeface(face);
            alert.show();
        } catch (Exception e) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
        return null;
    }


}
