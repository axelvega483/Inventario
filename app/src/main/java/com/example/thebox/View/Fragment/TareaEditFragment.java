package com.example.thebox.View.Fragment;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thebox.Data.Model.Tarea;
import com.example.thebox.R;
import com.example.thebox.ViewModel.TareasViewModel;


public class TareaEditFragment extends Fragment {
    private TareasViewModel tareasViewModel;
    private Tarea tarea;
    private EditText tituloTxt, descTxt;
    private TextView photo;
    private ImageView imgTarea;
    private Button saveEditTareaBtn;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private String imgUri;

    public TareaEditFragment() {
        // Required empty public constructor
    }

    public static TareaEditFragment newInstance(String id) {
        TareaEditFragment fragment = new TareaEditFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarImg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tarea_edit, container, false);
        init(rootView);
        if (getArguments() != null) {
            tareasViewModel.findById(Long.valueOf(getArguments().getString("id").toString())).observe(getViewLifecycleOwner(), tarea -> {
                this.tarea = tarea;
                setarDatos();
            });
        }
        initListeners();


        return rootView;
    }

    private void init(View rootView) {
        tareasViewModel = new ViewModelProvider(requireActivity()).get(TareasViewModel.class);
        tituloTxt = rootView.findViewById(R.id.tituloTxt);
        descTxt = rootView.findViewById(R.id.descTxt);
        imgTarea = rootView.findViewById(R.id.imgTarea);
        photo = rootView.findViewById(R.id.photo);
        saveEditTareaBtn = rootView.findViewById(R.id.saveEditTareaBtn);

    }

    private void initListeners() {
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityResultContracts.PickVisualMedia.isPhotoPickerAvailable()) {
                    pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE).build());
                }
            }
        });


        saveEditTareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tarea != null) {
                    // Asegúrate de actualizar los campos de la tarea antes de llamar al ViewModel
                    tarea.setTitulo(tituloTxt.getText().toString());
                    tarea.setDescripcion(descTxt.getText().toString());
                    tarea.setImgUri(imgUri);

                    tareasViewModel.update(tarea);
                    Toast.makeText(getActivity(), "Tarea actualizada", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    public void setarDatos() {
        if (tarea != null) {
            tituloTxt.setText(tarea.getTitulo());
            descTxt.setText(tarea.getDescripcion());
            Glide.with(this).load(tarea.getImgUri()).into(imgTarea);
        }
        if (tarea != null) {
            tarea.setTitulo(tituloTxt.getText().toString());
            tarea.setDescripcion(descTxt.getText().toString());
            tarea.setImgUri(imgUri); // Usar imgUri actualizado
        }
    }


    public void cargarImg() {
        // Registrar el ActivityResultLauncher para manejar la selección de imágenes o videos
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                imgTarea.setImageURI(uri);
                imgUri = uri.toString();
                Log.d("Selected Media URI", uri.toString());
            } else {
                // No se seleccionó ningún medio
                Log.d("Selected Media", "No media selected");
            }
        });
    }
}