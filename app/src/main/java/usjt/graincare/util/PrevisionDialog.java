package usjt.graincare.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import usjt.graincare.application.MainActivity;

public class PrevisionDialog {

    public void showPrediction(Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Previsão de abertura: ");
        // set dialog message
        alertDialogBuilder
                .setMessage("aproximadamente 67 dias restantes!");

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}