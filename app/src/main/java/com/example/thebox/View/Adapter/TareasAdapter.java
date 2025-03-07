package com.example.thebox.View.Adapter;

import static androidx.core.view.VelocityTrackerCompat.clear;
import static java.util.Collections.addAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.thebox.Data.Model.Tarea;
import com.example.thebox.R;
import com.example.thebox.View.Fragment.TareaDetalleFragment;
import com.example.thebox.ViewModel.TareasViewModel;

import java.util.ArrayList;
import java.util.List;

public class TareasAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private TareasViewModel tareasViewModel;
    private ArrayList<Tarea> tareas;

    public TareasAdapter(Context context, int layout, ArrayList<Tarea> tareas, TareasViewModel tareasViewModel) {
        this.context = context;
        this.layout = layout;
        this.tareas = tareas;
        this.tareasViewModel = tareasViewModel;
    }
    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int i) {
        return this.tareas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.titulo = convertView.findViewById(R.id.tituloTxt);
            holder.descripcion = convertView.findViewById(R.id.descTxt);
            holder.img = convertView.findViewById(R.id.imgTarea);
            holder.cardtarea = convertView.findViewById(R.id.cardtarea);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Tarea tarea = (Tarea) getItem(position);
        if (holder.titulo != null) {
            holder.titulo.setText(tarea.getTitulo());
        }
        if (holder.descripcion != null) {
            holder.descripcion.setText(tarea.getDescripcion());
        }
        if (holder.img != null && tarea.getImgUri() != null) {
            Glide.with(context)
                    .load(tarea.getImgUri())
                    .into(holder.img);
        }
        holder.cardtarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, TareaDetalleFragment.newInstance(tarea.getId().toString()))
                        .addToBackStack(null)
                        .commit();
            }
        });



        return convertView;
    }

    static class ViewHolder {
        TextView titulo;
        TextView descripcion;
        ImageView img;
        CardView cardtarea;
    }
    public void updateList(List<Tarea> newTareas) {

        addAll(newTareas);
        notifyDataSetChanged();
    }


}
