package com.example.thebox.Data.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.thebox.Data.Model.Producto;

import java.util.List;

@Dao
public interface ProductoDAO {
    @Query("SELECT * FROM Producto")
    LiveData<List<Producto>> findAll();

    @Query("SELECT * FROM Producto WHERE id = :id")
    LiveData<Producto> findById(long id);

    @Insert
    void save(Producto producto);

    @Update
    void update(Producto producto);

    @Delete
    void delete(Producto producto);

}
