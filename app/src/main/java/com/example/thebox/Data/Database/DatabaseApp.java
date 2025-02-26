package com.example.thebox.Data.Database;

import android.content.Context;

import androidx.room.*;
import androidx.room.RoomDatabase;

import com.example.thebox.Data.DAO.ProductoDAO;
import com.example.thebox.Data.Model.Productos;

@Database(entities = {Productos.class}, version = 1)
public abstract class DatabaseApp extends RoomDatabase {
    private static final String BD_NAME = "db_theBox";
    private static volatile DatabaseApp instance;


    public abstract ProductoDAO productoDAO();


    public static synchronized DatabaseApp getInstance(Context context) {
        if (instance == null) {

            synchronized (DatabaseApp.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseApp.class, BD_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
