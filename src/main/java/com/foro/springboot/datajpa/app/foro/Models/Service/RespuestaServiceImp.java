package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Dao.IRespuestaDao;
import com.foro.springboot.datajpa.app.foro.Models.Entity.Respuesta;

@Service
public class RespuestaServiceImp implements IRespuestaService{

    @Autowired
    private IRespuestaDao respuestaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> findAll() {
        return respuestaDao.findAll();
    }

    @Transactional
    @Override
    public void save(Respuesta respuesta) {
        respuestaDao.save(respuesta);
    }

    @Transactional(readOnly = true)
    @Override
    public Respuesta findOne(Long id) {
        return respuestaDao.findOne(id);

    }

    @Transactional
    @Override
    public void delete(Long id) {
        respuestaDao.delete(id);        
    }
}
