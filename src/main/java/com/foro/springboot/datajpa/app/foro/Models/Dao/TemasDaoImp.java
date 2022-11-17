package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Temas;

@Repository
public class TemasDaoImp implements ITemasDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Temas> findAll() {
        return em.createQuery("from Temas").getResultList();
    }

    @Override
    @Transactional
    public void save(Temas temas) {
        if(temas.getId() != null && temas.getId()>0){
            em.merge(temas);
        }else{
            em.persist(temas);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Temas findOne(Long id) {
        return em.find(Temas.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Temas temas = findOne(id);
        em.remove(temas);
    }
    
}
