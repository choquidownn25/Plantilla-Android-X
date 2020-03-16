package com.example.plantilla.encuesta;

import android.content.Context;
import android.content.Intent;
// import android.support.annotation.NonNull;
// import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantilla.Orden.ActivityOrden;
import com.example.plantilla.R;
import com.example.plantilla.ui.activity.MainActivityRecicleview;

import java.util.ArrayList;
public class EncuestaAdapter  extends RecyclerView.Adapter<EncuestaAdapter.ViewHolder>{

    ArrayList<String> personNames;
    ArrayList<String> personDescripcion;
    ArrayList<Integer> personImages;
    Context context;

    public EncuestaAdapter(Context context, ArrayList<String> personNames, ArrayList<String> personDescripcion, ArrayList<Integer> personImages) {
        this.context = context;
        this.personNames = personNames;
        this.personDescripcion = personDescripcion;
        this.personImages = personImages;
    }


    public ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // set the data in items
        holder.name.setText(personNames.get(position));
        holder.descripcion.setText(personDescripcion.get(position));
        holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                context.startActivity(new Intent(context, MainActivityFormulario.class));
                Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // init the item view's
        TextView name;
        TextView descripcion;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            descripcion= (TextView) itemView.findViewById(R.id.descripcion);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
