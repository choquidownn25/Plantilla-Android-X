package com.example.plantilla.video;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.plantilla.R;

public class MainActivityVideo extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public MainActivityVideo() {
    }
    public static MainActivityVideo newInstance(int sectionNumber) {
        MainActivityVideo fragment = new MainActivityVideo();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_video, container, false);
        // Inflar el diseño de este fragmento.


        VideoView videoView =(VideoView)view.findViewById(R.id.videoView1);

        //Creando MediaController
        MediaController mediaController= new MediaController(getContext());
        mediaController.setAnchorView(videoView);

        //especificar la ubicación del archivo multimedia
        Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/VID_20200529_055346350.mp4");

        //Configurando MediaController y URI, luego iniciando el videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        //return inflater.inflate(R.layout.fragment_tab_b, container, false);
        return view;
    }

 }
