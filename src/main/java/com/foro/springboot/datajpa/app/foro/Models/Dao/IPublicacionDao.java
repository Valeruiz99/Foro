package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Publicacion;

public interface IPublicacionDao {
    public List<Publicacion> findAll();

    public void save(Publicacion publicacion);

    public Publicacion findOne(Long id);

    public void delete(Long id);
}
