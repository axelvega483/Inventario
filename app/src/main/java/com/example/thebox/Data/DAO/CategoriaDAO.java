package com.example.thebox.Data.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.thebox.Data.Model.Categoria;

import java.util.List;

@Dao
public interface CategoriaDAO {
    @Query("SELECT * FROM Categoria")
    LiveData<List<Categoria>> findAll();

    @Query("SELECT * FROM Categoria WHERE id = :id")
    LiveData<Categoria> findById(long id);

    @Insert
    void save(Categoria categoria);

    @Update
    void update(Categoria categoria);

    @Delete
    void delete(Categoria categoria);
}
