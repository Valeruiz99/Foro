package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Dao.IComentarioDao;
import com.foro.springboot.datajpa.app.foro.Models.Entity.Comentario;

@Service
public class ComentarioServiceImp implements IComentarioService {

    @Autowired
    private IComentarioDao comentarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return comentarioDao.findAll();
    }

    @Override
    @Transactional
    public void save(Comentario comentario) {
        comentarioDao.save(comentario);
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario findOne(Long id) {
        return comentarioDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        comentarioDao.delete(id);        
    }
    
}
