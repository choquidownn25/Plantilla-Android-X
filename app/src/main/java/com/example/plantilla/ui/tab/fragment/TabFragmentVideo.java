package com.example.plantilla.ui.tab.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.example.plantilla.R;

public class TabFragmentVideo  extends Fragment  {

    public TabFragmentVideo() {
        // Constructor público vacío requerido
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
