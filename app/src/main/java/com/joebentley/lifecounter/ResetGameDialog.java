package com.joebentley.lifecounter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by joe on 08/02/2016.
 */
public class ResetGameDialog extends DialogFragment {

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.reset_dialog_message)
               .setPositiveButton(R.string.reset_dialog_yes, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       ScoreBoard.get(getActivity()).resetScoreBoard();
                       sendResult(Activity.RESULT_OK);
                   }
               })
               .setNegativeButton(R.string.reset_dialog_no, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       ScoreBoard.get(getActivity()).getCurrentScore().resetToDefault();
                       sendResult(Activity.RESULT_CANCELED);
                   }
               });

        return builder.create();
    }


    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
