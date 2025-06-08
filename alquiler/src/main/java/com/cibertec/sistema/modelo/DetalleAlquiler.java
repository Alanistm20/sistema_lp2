package com.cibertec.sistema.modelo;
import jakarta.persistence.*;
@Entity
@IdClass(DetalleAlquilerId.class)
public class DetalleAlquiler {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_alquiler",nullable = false)
    private Alquiler alquiler;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pelicula",nullable = false)
    private Pelicula pelicula;

    private int cantidad;

    public Pelicula getPelicula() {return pelicula;
    } public void setPelicula(Pelicula pelicula) {this.pelicula = pelicula;
    }

    public int getCantidad() {return cantidad;
    }public void setCantidad(int cantidad) {this.cantidad = cantidad;
    }


        public DetalleAlquiler() {
        // Constructor por defecto
}
    
    public DetalleAlquiler(Pelicula pelicula, int cantidad) {
        
        this.pelicula = pelicula;
        this.cantidad = cantidad;
    }

    public DetalleAlquiler(Alquiler alquiler, Pelicula pelicula, int cantidad) {
    this.alquiler = alquiler;
    this.pelicula = pelicula;
    this.cantidad = cantidad;
}


    public Alquiler getAlquiler() {return alquiler; }
    public void setAlquiler(Alquiler alquiler) 
    { this.alquiler = alquiler;       
    }
    
    // Getters y setters
}

    

