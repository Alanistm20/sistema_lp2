package com.cibertec.sistema.dao;

import com.cibertec.sistema.modelo.Pelicula;
import com.cibertec.sistema.util.JPAUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class PeliculaDAO {
    public static List<Pelicula> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList();
    }
}
