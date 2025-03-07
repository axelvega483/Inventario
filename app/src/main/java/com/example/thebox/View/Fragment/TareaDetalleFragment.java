package com.example.thebox.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thebox.Data.Model.Tarea;
import com.example.thebox.R;
import com.example.thebox.ViewModel.TareasViewModel;


public class TareaDetalleFragment extends Fragment {
    private Button editTareaBtn, deleteTareaBtn;
    private TareasViewModel tareasViewModel;
    private Tarea tarea;
    private TextView tituloDetalleTxt, descDetalleTxt;
    private ImageView imgDetalleTarea;

    public TareaDetalleFragment() {
        // Required empty public constructor
    }


    public static TareaDetalleFragment newInstance(String id) {
        TareaDetalleFragment fragment = new TareaDetalleFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tarea_detalle, container, false);
        init(rootView);
        if (getArguments() != null) {
            tareasViewModel.findById(Long.valueOf(getArguments().getString("id").toString())).observe(getViewLifecycleOwner(), tarea -> {
                this.tarea = tarea;
                setarDatos();
            });
        }
        initListeners();
        setarDatos();
        return rootView;
    }

    private void init(View rootView) {
        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);
        editTareaBtn = rootView.findViewById(R.id.editTareaBtn);
        deleteTareaBtn = rootView.findViewById(R.id.deleteTareaBtn);
        tituloDetalleTxt = rootView.findViewById(R.id.tituloDetalleTxt);
        descDetalleTxt = rootView.findViewById(R.id.descDetalleTxt);
        imgDetalleTarea = rootView.findViewById(R.id.imgDetalleTarea);
    }

    private void initListeners() {
        editTareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tarea != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer, TareaEditFragment.newInstance(String.valueOf(tarea.getId())))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        deleteTareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tarea != null) {
                    tareasViewModel.delete(tarea);
                    Toast.makeText(getActivity(), "Tarea eliminada", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

    }

    public void setarDatos() {
        if (tarea != null) {
            tituloDetalleTxt.setText(tarea.getTitulo());
            descDetalleTxt.setText(tarea.getDescripcion());
            Glide.with(this).load(tarea.getImgUri()).into(imgDetalleTarea);
        }
    }


}