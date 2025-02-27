package com.example.thebox.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thebox.Data.DAO.TareasDAO;
import com.example.thebox.Data.Database.DatabaseApp;
import com.example.thebox.Data.Model.Tarea;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TareasViewModel extends AndroidViewModel {
    private final TareasDAO tareasDAO;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TareasViewModel(@NonNull Application application) {
        super(application);
        tareasDAO = DatabaseApp.getInstance(application).tareasDAO();
    }

    protected void onCleared() {
        super.onCleared();
        //cierre de hilo
        executorService.shutdown();
    }

    public LiveData<List<Tarea>> findAllTareas() {
        return tareasDAO.findAll();
    }

    public LiveData<Tarea> findById(Long id) {
        return tareasDAO.findById(id);
    }


    public void save(Tarea tarea) {
        executorService.execute(() -> {
            try {
                tareasDAO.save(tarea);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

    public void update(Tarea tarea) {
        executorService.execute(() -> {
            try {
                tareasDAO.update(tarea);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

    public void delete(Tarea tarea) {
        executorService.execute(() -> {
            try {
                tareasDAO.delete(tarea);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

}
