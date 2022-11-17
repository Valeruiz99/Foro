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

import com.foro.springboot.datajpa.app.foro.Models.Entity.Comentario;
import com.foro.springboot.datajpa.app.foro.Models.Service.IComentarioService;
import com.foro.springboot.datajpa.app.foro.Models.Service.IPublicacionService;
import com.foro.springboot.datajpa.app.foro.Models.Service.IUsuariosService;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {
    
    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IPublicacionService publicacionService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("usuarios", usuariosService.findAll());
        model.addAttribute("publicaciones", publicacionService.findAll());
        model.addAttribute("titulo", "Lista de comentarios");
        model.addAttribute("comentarios", comentarioService.findAll());

        return "listarComentarios";
    }
    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Comentario comentario = new Comentario();
        model.put("comentarios", comentario);
        model.put("usuarios", usuariosService.findAll());
        model.put("publicaciones", publicacionService.findAll());
        model.put("titulo", "Formulario de comentarios");
        return "formComentarios";
    }
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Comentario comentario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Error al crear el comentario");
            return "formComentarios";
        }
        comentarioService.save(comentario);
        return "redirect:/comentarios/listar";
    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id,Map<String,Object>model){
        Comentario comentario = null;
        if(id>0){
            comentario = comentarioService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("comentarios", comentario);
        model.put("usuarios", usuariosService.findAll());
        model.put("publicaciones", publicacionService.findAll());
        model.put("titulo", "Editar comentario");
        return "formComentarios";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        if(id>0)
            comentarioService.delete(id);
        return "redirect:/comentarios/listar";
    }
}
