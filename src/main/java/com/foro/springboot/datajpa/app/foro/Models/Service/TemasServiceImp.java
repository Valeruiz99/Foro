package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Dao.ITemasDao;
import com.foro.springboot.datajpa.app.foro.Models.Entity.Temas;

@Service
public class TemasServiceImp implements ITemasService {

    @Autowired
    private ITemasDao temasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Temas> findAll() {
        return temasDao.findAll();
    }

    @Override
    @Transactional
    public void save(Temas temas) {
        temasDao.save(temas);
    }

    @Transactional(readOnly = true)
    @Override
    public Temas findOne(Long id) {
        return temasDao.findOne(id);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        temasDao.delete(id);
        
    }
    
}
