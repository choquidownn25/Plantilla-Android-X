package com.example.plantilla.encuesta;

import android.os.Bundle;
// import android.support.annotation.Nullable;
// import android.support.design.widget.CollapsingToolbarLayout;
// import android.support.v7.app.AppCompatActivity;

import com.example.plantilla.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
// import android.support.v7.app.ActionBar;
// import android.support.v7.app.AppCompatActivity;
// import android.support.v7.widget.LinearLayoutManager;
// import android.support.v7.widget.RecyclerView;
// import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityEncuesta extends AppCompatActivity {

    // ArrayList para eventos
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    ArrayList<String> personNames = new ArrayList<>(Arrays.asList("Evento 1", "Evento 2", "Evento 3", "Evento 4", "Evento 5", "Evento 6", "Evento 7","Evento 8", "Evento 9", "Evento 10", "Evento 11", "Evento 12", "Evento 13", "Evento 14"));
    ArrayList<String> personDescripcion = new ArrayList<>(Arrays.asList("Tecnologia", "Ecologia", "Ciencias", "Biologia", "Matematicas", "Fisica", "Cultura", "Historia","Literatura", "Cine", "Television", "Deportes", "Juegos", "Entretenimiento"));
    ArrayList<Integer> personImages = new ArrayList<>(Arrays.asList(R.drawable.pediatria, R.drawable.fate, R.drawable.webrtcportalconetividad, R.drawable.webrtc, R.drawable.webrtcportal, R.drawable.fate, R.drawable.consulta,R.drawable.apaga, R.drawable.camara, R.drawable.angel, R.drawable.citamedica, R.drawable.histoclinica, R.drawable.java, R.drawable.chat));
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.saludo) );

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        EncuestaAdapter customAdapter = new EncuestaAdapter(MainActivityEncuesta.this, personNames,personDescripcion,personImages);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }
}
