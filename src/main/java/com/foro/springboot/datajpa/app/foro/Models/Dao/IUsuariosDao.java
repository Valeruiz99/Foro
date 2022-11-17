package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Usuario;

public interface IUsuariosDao {
    public List<Usuario> findAll();

    public void save(Usuario usuario);

    public Usuario findOne(Long id);

    public void delete(Long id);
}
