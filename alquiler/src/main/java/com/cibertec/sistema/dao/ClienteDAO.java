package com.cibertec.sistema.dao;

import com.cibertec.sistema.modelo.Cliente;
import com.cibertec.sistema.util.JPAUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {
    public static List<Cliente> obtenerTodos() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}
