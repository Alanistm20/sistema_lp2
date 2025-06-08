package com.cibertec.sistema.modelo;


import jakarta.persistence.*;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    @Column(nullable = false, length = 50)
    private String nombre;

   @Column(nullable = false, length = 50)
    private String email;

   public String getNombre() {return nombre;   }

   public void setNombre(String nombre) {this.nombre = nombre;
   }

   public String getEmail() {return email;
   }

   public void setEmail(String email) {this.email = email;
   }


   @Override
public String toString() {
    return nombre + " (" + email + ")";
}

    // Getters y setters
}



