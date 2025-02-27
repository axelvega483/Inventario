package com.example.thebox.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thebox.R;
import com.example.thebox.databinding.TareaFragmentBinding;

public class TareaFragment extends Fragment {
    private TareaFragmentBinding binding;

    public static TareaFragment newInstance() {
        TareaFragment fragment = new TareaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public TareaFragment() {
        // Constructor vacío requerido por Fragment
    }

    // Inflar el layout del fragmento en onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout usando ViewBinding
        binding = TareaFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    // Aquí manejas la lógica una vez que la vista ya está creada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
    }

    // Inicializar los listeners
    private void initListeners() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment addFragment = new AddFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, addFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    // Limpiar el binding para evitar fugas de memoria
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
