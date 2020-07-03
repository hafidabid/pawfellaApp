package com.zakiyah.Pawfella.pawapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class dialogkamingsun extends AppCompatDialogFragment {
    String judul = "";
    String pesan = "";
    public dialogkamingsun(){

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bangun= new AlertDialog.Builder(getActivity());
        bangun.setTitle(judul)
                .setMessage(pesan)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return bangun.create();
    }
}
