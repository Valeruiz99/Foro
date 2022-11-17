package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.springboot.datajpa.app.foro.Models.Dao.IUsuariosDao;
import com.foro.springboot.datajpa.app.foro.Models.Entity.Usuario;

@Service
public class UsuariosServiceImp implements IUsuariosService{

    @Autowired
    private IUsuariosDao usuariosDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuariosDao.findAll();
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuariosDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findOne(Long id) {
        return usuariosDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuariosDao.delete(id);        
    }
    
}
