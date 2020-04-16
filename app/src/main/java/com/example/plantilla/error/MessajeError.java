package com.example.plantilla.error;

import android.app.Activity;
import android.content.Context;

import com.example.plantilla.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.core.content.res.TypedArrayUtils.getString;

public class MessajeError {

    public   void errorMessege(Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show();
    }

    public void errorAutenticacion(Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(context.getString(R.string.channel_error_title))
                .setContentText(context.getString(R.string.errorautentucacionred))
                .show();
    }

}
