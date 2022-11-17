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

import com.foro.springboot.datajpa.app.foro.Models.Entity.Usuario;
import com.foro.springboot.datajpa.app.foro.Models.Service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
    
    @Autowired
    private IUsuariosService usuariosService;


    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Lista de usuarios");
        model.addAttribute("usuarios", usuariosService.findAll());

        return "listarUsuarios";
    }

    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Usuario usuario = new Usuario();
        model.put("usuarios", usuario);
        model.put("titulo", "Formulario de usuarios");
        return "formUsuarios";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Error al crear el usuario");
            return "formUsuarios";
        }
        usuariosService.save(usuario);
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id,Map<String,Object>model){
        Usuario usuario = null;
        if(id>0){
            usuario = usuariosService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("usuarios", usuario);
        model.put("titulo", "Editar usuario");
        return "formUsuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        if(id>0)
            usuariosService.delete(id);
        return "redirect:/usuarios/listar";
    }
}
