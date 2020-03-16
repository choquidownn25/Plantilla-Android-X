package com.example.plantilla.encuesta;

import android.os.Bundle;
// import android.support.annotation.Nullable;
// import android.support.design.widget.CollapsingToolbarLayout;
// import android.support.v7.app.ActionBar;
// import android.support.v7.app.AppCompatActivity;

import com.example.plantilla.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
// import android.support.v7.widget.LinearLayoutManager;
// import android.support.v7.widget.RecyclerView;
// import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivityFormulario extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private  Button demo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.saludo) );
        demo = (Button)findViewById(R.id.demo);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertOneButton();
            }
        });
    }

    /*
     * AlertDialog with one action button.
     */
    public void alertOneButton() {

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Encuesta!")
                .setContentText("Gracias por la responder!")
                .show();
    }
}
