package com.foro.springboot.datajpa.app.foro.Models.Dao;

import java.util.List;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Respuesta;

public interface IRespuestaDao {
    public List<Respuesta> findAll();

    public void save(Respuesta respuesta);

    public Respuesta findOne(Long id);

    public void delete(Long id);
}
