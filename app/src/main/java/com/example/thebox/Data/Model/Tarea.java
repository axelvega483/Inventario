package com.example.thebox.Data.Model;

import androidx.room.*;

@Entity(tableName = "tarea")
public class Tarea {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descripcion;
    private String imgUri;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, String imgUri) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imgUri = imgUri;
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

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
