package com.example.thebox.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thebox.Data.DAO.CategoriaDAO;
import com.example.thebox.Data.Database.DatabaseApp;
import com.example.thebox.Data.Model.Categoria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoriaViewModel extends AndroidViewModel {
    private final CategoriaDAO categoriaDAO;
    private final ExecutorService executorService= Executors.newSingleThreadExecutor();
    public CategoriaViewModel(@NonNull Application application) {
        super(application);
        categoriaDAO = DatabaseApp.getInstance(application).categoriaDAO();
    }
    protected void onCleared() {
        super.onCleared();
        //cierre de hilo
        executorService.shutdown();
    }
    public LiveData<List<Categoria>> findAllCategorias(){
        return categoriaDAO.findAll();
    }
    public LiveData<Categoria> findById(Long id){
        return categoriaDAO.findById(id);
    }

    public void save(Categoria categoria){
        executorService.execute(()-> {
            try{
                categoriaDAO.save(categoria);
            }catch (Exception e){
                Log.e("Error",e.getMessage());
            }
        });
    }

    public void update(Categoria categoria) {
        executorService.execute(() -> {
            try {
                categoriaDAO.update(categoria);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

    public void delete(Categoria categoria) {
        executorService.execute(() -> {
            try {
                categoriaDAO.delete(categoria);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

}
