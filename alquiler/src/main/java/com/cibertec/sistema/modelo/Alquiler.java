package com.cibertec.sistema.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alquiler;

    @Column(nullable = false, length = 50)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoAlquiler estado;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleAlquiler> detalles = new ArrayList<>();

    public LocalDate getFecha() {return fecha;
    }
    public void setFecha(LocalDate fecha) {this.fecha = fecha;
    }
    public EstadoAlquiler getEstado() {return estado;
    }
    public void setEstado(EstadoAlquiler estado) {this.estado = estado;
    }
    public Double getTotal() {return total;
    }
    public void setTotal(Double total) {  this.total = total;    }
    public Cliente getCliente() { return cliente;
    }
    public void setCliente(Cliente cliente) { this.cliente = cliente;
    }
    public List<DetalleAlquiler> getDetalles() {return detalles;
    }
    public void setDetalles(List<DetalleAlquiler> detalles) {this.detalles = detalles;
    }

    public void agregarDetalle(DetalleAlquiler detalle) {
    this.detalles.add(detalle);
    detalle.setAlquiler(this);
    
}

    // Getters y setters
}
