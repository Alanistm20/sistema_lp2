package com.cibertec.sistema.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;







@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(nullable = false, length = 50)
    private String titulo;
    @Column(nullable = false, length = 30)
    private String genero;
     private int stock;
    
    public Pelicula() {}

    
     public Pelicula(String titulo, String genero, int stock) {
        this.titulo = titulo;
        this.genero = genero;
        this.stock = stock;
    }
     public Long getId() {
         return id;
     }
     public void setId(Long id) {
         this.id = id;
     }
     public String getTitulo() {return titulo;
     }
     public void setTitulo(String titulo) {this.titulo = titulo;
     }
     public String getGenero() {return genero;
     }
     public void setGenero(String genero) {
         this.genero = genero;
     }

     public int getStock() { return stock;
     }

     public void setStock(int stock) {
         this.stock = stock;
     }
       
    // Getters y Setters
       @Override
public String toString() {
    return titulo + " (" + genero + ")";
}


}


