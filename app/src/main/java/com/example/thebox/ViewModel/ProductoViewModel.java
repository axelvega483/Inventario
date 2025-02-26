package com.example.thebox.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thebox.Data.DAO.ProductoDAO;
import com.example.thebox.Data.Database.DatabaseApp;
import com.example.thebox.Data.Model.Productos;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductoViewModel extends AndroidViewModel {
    private final ProductoDAO productoDAO;
    private final ExecutorService executorService= Executors.newSingleThreadExecutor();

    public ProductoViewModel(@NonNull Application application) {
        super(application);
        productoDAO = DatabaseApp.getInstance(application).productoDAO();
    }

    protected void onCleared() {
        super.onCleared();
        //cierre de hilo
        executorService.shutdown();
    }
    public LiveData<List<Productos>> findAllProductos(){
        return productoDAO.findAll();
    }
    public LiveData<Productos> findById(int id){
        return productoDAO.findById(id);
    }


    public void save(Productos producto){
        executorService.execute(()-> {
            try{
                productoDAO.save(producto);
            }catch (Exception e){
                Log.e("Error",e.getMessage());
            }
        });
    }

    public void update(Productos producto) {
        executorService.execute(() -> {
            try {
                productoDAO.update(producto);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

    public void delete(Productos producto) {
        executorService.execute(() -> {
            try {
                productoDAO.delete(producto);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
    }

}
