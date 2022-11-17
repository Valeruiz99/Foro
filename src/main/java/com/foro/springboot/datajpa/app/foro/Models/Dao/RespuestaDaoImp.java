package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Respuesta;

@Repository
public class RespuestaDaoImp implements IRespuestaDao{

    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Respuesta> findAll() {
        return em.createQuery("from Respuesta").getResultList();

    }

    @Override
    @Transactional
    public void save(Respuesta respuesta) {
        if(respuesta.getId() != null && respuesta.getId()>0){
            em.merge(respuesta);
        }else{
            em.persist(respuesta);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Respuesta findOne(Long id) {
        return em.find(Respuesta.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Respuesta respuesta = findOne(id);
        em.remove(respuesta);  
    }
}
