package com.foro.springboot.datajpa.app.foro.Models.Service;

import java.util.List;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Temas;

public interface ITemasService {
    public List<Temas> findAll();

    public void save(Temas temas);

    public Temas findOne(Long id);

    public void delete(Long id);
}
