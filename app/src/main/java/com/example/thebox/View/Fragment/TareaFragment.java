package com.example.thebox.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.thebox.Data.Model.Tarea;
import com.example.thebox.R;
import com.example.thebox.View.Adapter.TareasAdapter;
import com.example.thebox.ViewModel.TareasViewModel;

import java.util.ArrayList;

public class TareaFragment extends Fragment {

    private ListView listView;
    private TareasAdapter tareaAdapter;
    private TareasViewModel tareasViewModel;
    private Button addBtn;


    public static TareaFragment newInstance() {
        TareaFragment fragment = new TareaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TareaFragment() {
        // Constructor vacío requerido por Fragment
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.tarea_fragment, container, false);
        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);

        init(rootView);
        setearDatos();
        return rootView;
    }

    private void init(View rootView) {
        listView = rootView.findViewById(R.id.listTareas);

        addBtn = rootView.findViewById(R.id.addBtn);

    }

    private void setearDatos() {
        // Observamos el LiveData para actualizar el adaptador cuando cambien las tareas
        tareasViewModel.findAllTareas().observe(getViewLifecycleOwner(), tareas -> {
            // Actualiza la lista del adaptador y notifica el cambio
            tareaAdapter= new TareasAdapter(getContext(), R.layout.item_tarea, (ArrayList<Tarea>) tareas, tareasViewModel);
            listView.setAdapter(tareaAdapter);
            if (tareas.size() > 0) {
                tareaAdapter.updateList(tareas);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar al fragmento de agregar tarea
                Add_Tareas_Fragment addFragment = Add_Tareas_Fragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, addFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
