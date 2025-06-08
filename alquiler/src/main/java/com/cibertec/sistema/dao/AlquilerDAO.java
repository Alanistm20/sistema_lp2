package com.cibertec.sistema.dao;

import com.cibertec.sistema.modelo.*;

import com.cibertec.sistema.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;


public class AlquilerDAO {
    public static void guardarConDetalles(Alquiler alquiler, java.util.List<DetalleAlquiler> detalles) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(alquiler);
        em.flush(); 

            for (DetalleAlquiler detalle : detalles) {
                
                 Pelicula pelicula = em.find(Pelicula.class, detalle.getPelicula().getId(), 
                    LockModeType.PESSIMISTIC_WRITE);

                

                // ✅ Validación de stock
                if (pelicula.getStock() < detalle.getCantidad()) {
                    throw new IllegalArgumentException("No hay suficiente stock de la película: " 
                        + pelicula.getTitulo());
                }

// Establecer la relación bidireccional
            detalle.setAlquiler(alquiler);
            alquiler.getDetalles().add(detalle);
            
            // Actualizar stock
            pelicula.setStock(pelicula.getStock() - detalle.getCantidad());
            
            // Persistir el detalle
            em.persist(detalle);
            em.merge(pelicula);
        }
        
        tx.commit();
    } catch (Exception e) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        throw new RuntimeException("Error al guardar alquiler: " + e.getMessage(), e);
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    }
}

