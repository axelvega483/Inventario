package com.example.thebox.Data.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.thebox.Data.Model.Tareas;

import java.util.List;

@Dao
public interface TareasDAO {
    @Query("SELECT * FROM tarea")
    LiveData<List<Tareas>> findAll();

    @Query("SELECT * FROM tarea WHERE id = :id")
    LiveData<Tareas> findById(long id);

    @Insert
    void save(Tareas tarea);

    @Update
    void update(Tareas tarea);

    @Delete
    void delete(Tareas tarea);

}
