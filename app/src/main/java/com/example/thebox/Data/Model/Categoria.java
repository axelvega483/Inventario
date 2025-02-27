package com.example.thebox.Data.Model;

import androidx.room.*;


@Entity(tableName = "categoria")
public class Categoria {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
