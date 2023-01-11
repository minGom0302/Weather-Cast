package com.example.weathercast.etc;


import android.app.AlertDialog;
import android.content.Context;

public class Dialog {

    public static void showNormalDialog(Context context, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(msg);
        builder.setPositiveButton("예", (dialogInterface, i) -> {

        });
        builder.setNegativeButton("아니오", (dialogInterface, i) -> {

        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
