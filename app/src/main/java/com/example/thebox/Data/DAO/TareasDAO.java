package com.example.thebox.Data.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.thebox.Data.Model.Tarea;

import java.util.List;

@Dao
public interface TareasDAO {
    @Query("SELECT * FROM Tarea")
    LiveData<List<Tarea>> findAll();

    @Query("SELECT * FROM Tarea WHERE id = :id")
    LiveData<Tarea> findById(long id);

    @Insert
    void save(Tarea tarea);

    @Update
    void update(Tarea tarea);

    @Delete
    void delete(Tarea tarea);

}
