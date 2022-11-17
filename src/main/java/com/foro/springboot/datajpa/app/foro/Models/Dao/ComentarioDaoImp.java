package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Comentario;

@Repository
public class ComentarioDaoImp implements IComentarioDao {

    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Comentario> findAll() {
        return em.createQuery("from Comentario").getResultList();
    }

    @Override
    @Transactional
    public void save(Comentario comentario) {
        if(comentario.getId() != null && comentario.getId()>0){
            em.merge(comentario);
        }else{
            em.persist(comentario);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Comentario findOne(Long id) {
        return em.find(Comentario.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Comentario comentario = findOne(id);
        em.remove(comentario);        
    }
}
