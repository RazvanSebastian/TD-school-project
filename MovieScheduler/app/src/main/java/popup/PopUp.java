package popup;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;


import com.moviescheduler.activity.NewUserSpectacleActivity;

import pojo.Spectacle;

/**
 * Created by Parautiu on 15.05.2017.
 */

public class PopUp {

    public void openNewPopOnSelectSpectacle(final FragmentActivity fa,final Context context, String title, String message, final Spectacle spectacleToPass) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        final Intent intent = new Intent(fa,NewUserSpectacleActivity.class);
        // set title
        alertDialogBuilder.setTitle(title);



        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        intent.putExtra("SpectacleDescription",spectacleToPass.toString());
                        intent.putExtra("SpectacleId",spectacleToPass.getIdSpectacle());
                        fa.startActivity(intent);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}
