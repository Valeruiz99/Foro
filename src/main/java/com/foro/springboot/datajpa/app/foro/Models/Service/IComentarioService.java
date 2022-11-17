package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Comentario;

public interface IComentarioService {
    public List<Comentario> findAll();

    public void save(Comentario comentario);

    public Comentario findOne(Long id);

    public void delete(Long id);
}
