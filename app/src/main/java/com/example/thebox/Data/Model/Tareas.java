package com.example.thebox.Data.Model;

import androidx.room.*;

@Entity(tableName = "tarea")
public class Tareas {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descripcion;

    public Tareas(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
