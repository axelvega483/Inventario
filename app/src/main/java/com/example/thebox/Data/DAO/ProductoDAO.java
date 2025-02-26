package com.example.thebox.Data.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.thebox.Data.Model.Productos;

import java.util.List;

@Dao
public interface ProductoDAO {
    @Query("SELECT * FROM producto")
    LiveData<List<Productos>> findAll();

    @Query("SELECT * FROM producto WHERE id = :id")
    LiveData<Productos> findById(long id);

    @Insert
    void save(Productos producto);

    @Update
    void update(Productos producto);

    @Delete
    void delete(Productos producto);

}
