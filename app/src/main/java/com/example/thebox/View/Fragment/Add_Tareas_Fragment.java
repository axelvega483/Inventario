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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebox.Data.Model.Tarea;
import com.example.thebox.R;
import com.example.thebox.ViewModel.TareasViewModel;


public class Add_Tareas_Fragment extends Fragment {

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private TareasViewModel viewModelTarea;
    private String imgUri;
    private TextView photo, tituloTxt, descTxt;
    private ImageView imgTarea;
    private Button addTareaBtn;

    public Add_Tareas_Fragment() {
        // Required empty public constructor
    }


    public static Add_Tareas_Fragment newInstance() {
        Add_Tareas_Fragment fragment = new Add_Tareas_Fragment();
        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.add_tareas_fragment,container,false);
        init(rootView);
        initListeners();
        setarDatos();
        return rootView;
    }



    private void init(View rootView) {
        photo = rootView.findViewById(R.id.photo);
        imgTarea = rootView.findViewById(R.id.imgTarea);
        addTareaBtn = rootView.findViewById(R.id.addTareaBtn);
        tituloTxt = rootView.findViewById(R.id.tituloTxt);
        descTxt = rootView.findViewById(R.id.descTxt);
        viewModelTarea = new ViewModelProvider(this).get(TareasViewModel.class);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityResultContracts.PickVisualMedia.isPhotoPickerAvailable()) {
                    pickMedia.launch(new PickVisualMediaRequest.Builder()
                            .setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE)
                            .build());
                }
            }
        });

        addTareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = tituloTxt.getText().toString();
                String descripcion = descTxt.getText().toString();
                Log.d("Tarea", "Título: " + titulo + ", Descripción: " + descripcion);  // Añadir log para ver los datos

                Tarea tarea = new Tarea();
                tarea.setTitulo(titulo);
                tarea.setDescripcion(descripcion);
                tarea.setImgUri(imgUri);

                // Agregar la tarea al ViewModel
                viewModelTarea.save(tarea);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
    private void initListeners() {

    }

    public void cargarImg() {
        // Registrar el ActivityResultLauncher para manejar la selección de imágenes o videos
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                // Actualizar la variable imgUri cuando se seleccione una imagen
                 imgTarea.setImageURI(uri);
                imgUri = uri.toString();
                Log.d("Selected Media URI", uri.toString());
            } else {
                // No se seleccionó ningún medio
                Log.d("Selected Media", "No media selected");
            }
        });
    }
    public void setarDatos(){

    }
}