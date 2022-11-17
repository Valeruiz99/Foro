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

import com.foro.springboot.datajpa.app.foro.Models.Entity.Temas;
import com.foro.springboot.datajpa.app.foro.Models.Service.ITemasService;

@Controller
@RequestMapping("/temas")
public class TemasController {
    
    @Autowired
    private ITemasService temasService;

    @GetMapping("/listar")
    public String Listar(Model model){
        model.addAttribute("titulo", "Lista de temas");
        model.addAttribute("temas", temasService.findAll());

        return "listarTemas";
    }

    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Temas temas = new Temas();
        model.put("temas", temas);
        model.put("titulo", "Formulario de temas");

        return "formTemas";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Temas temas, BindingResult result, Model model){
        if(result.hasErrors()){
        model.addAttribute("titulo", "Error al crear el tema");
        return "formTemas";
        }
        temasService.save(temas);
        return "redirect:/temas/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id,Map<String,Object>model){
        Temas temas = null;
        if(id>0){
            temas = temasService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("temas", temas);
        model.put("titulo", "Editar tema");
        return "formTemas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        if(id>0)
            temasService.delete(id);
        return "redirect:/temas/listar";
    }
}
