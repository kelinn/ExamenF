package com.example.android.examen_ii_anthonymarin_kelinsilva;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "lote_db")
public class Lote implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "mts")
    private Double mts;

    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "video")
    private String video;

    @ColumnInfo(name = "imagen")
    private String imagen;

    public Lote(@NonNull String nombre, Double mts, String fecha, String video, String imagen) {
        this.nombre = nombre;
        this.mts = mts;
        this.fecha = fecha;
        this.video = video;
        this.imagen = imagen;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public Double getMts() {
        return mts;
    }

    public void setMts(Double mts) {
        this.mts = mts;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
