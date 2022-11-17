package com.foro.springboot.datajpa.app.foro.Controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foro.springboot.datajpa.app.foro.Models.Entity.Publicacion;
import com.foro.springboot.datajpa.app.foro.Models.Service.IPublicacionService;
import com.foro.springboot.datajpa.app.foro.Models.Service.ITemasService;
import com.foro.springboot.datajpa.app.foro.Models.Service.IUsuariosService;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionController {
    
    @Autowired
    private IPublicacionService publicacionService;
    
    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ITemasService temasService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Lista de publicaciones");
        model.addAttribute("temas", temasService.findAll());
        model.addAttribute("publicaciones", publicacionService.findAll());
        model.addAttribute("usuarios", usuariosService.findAll());

        return "listarPublicaciones";
    }
    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Publicacion publicacion = new Publicacion();
        model.put("temas", temasService.findAll());
        model.put("usuarios", usuariosService.findAll());
        model.put("publicaciones", publicacion);
        model.put("titulo", "Formulario de publicaciones");
        return "formPublicaciones";
    }
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Publicacion publicacion, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Error al crear la publicación");
            return "formPublicaciones";
        }
        publicacionService.save(publicacion);
        return "redirect:/publicaciones/listar";
    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id,Map<String,Object>model){
        Publicacion publicacion = null;
        if(id>0){
            publicacion = publicacionService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("publicaciones", publicacion);
        model.put("usuarios", usuariosService.findAll());
        model.put("temas", temasService.findAll());
        model.put("titulo", "Editar publicación");
        return "formPublicaciones";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        if(id>0)
            publicacionService.delete(id);
        return "redirect:/publicaciones/listar";
    }
}
