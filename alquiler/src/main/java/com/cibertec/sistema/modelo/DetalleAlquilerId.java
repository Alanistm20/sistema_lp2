package com.cibertec.sistema.modelo;

import java.io.Serializable;
import java.util.Objects;

public class DetalleAlquilerId implements Serializable {

    private Integer alquiler;
    private Long pelicula;

    public DetalleAlquilerId() {}

    public DetalleAlquilerId(Integer alquiler, Long pelicula) {
        this.alquiler = alquiler;
        this.pelicula = pelicula;
    }

public Integer getAlquiler() { return alquiler; }
    public void setAlquiler(Integer alquiler) { this.alquiler = alquiler; }

    public Long getPelicula() { return pelicula; }
    public void setPelicula(Long pelicula) { this.pelicula = pelicula; }



    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleAlquilerId)) return false;
        DetalleAlquilerId that = (DetalleAlquilerId) o;
        return Objects.equals(alquiler, that.alquiler) &&
               Objects.equals(pelicula, that.pelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alquiler, pelicula);
    }
}
