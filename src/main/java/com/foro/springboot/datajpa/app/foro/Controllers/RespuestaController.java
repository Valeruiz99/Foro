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

import com.foro.springboot.datajpa.app.foro.Models.Entity.Respuesta;
import com.foro.springboot.datajpa.app.foro.Models.Service.IComentarioService;
import com.foro.springboot.datajpa.app.foro.Models.Service.IRespuestaService;
import com.foro.springboot.datajpa.app.foro.Models.Service.IUsuariosService;

@Controller
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private IRespuestaService respuestaService;

    @Autowired
    private IUsuariosService usuariosService;
    
    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("usuarios", usuariosService.findAll());
        model.addAttribute("comentarios", comentarioService.findAll());
        model.addAttribute("titulo", "Lista de respuestas");
        model.addAttribute("respuestas", respuestaService.findAll());

        return "listarRespuestas";
    }
    @GetMapping("/form")
    public String crear(Map<String,Object> model){
        Respuesta respuesta = new Respuesta();
        model.put("usuarios", usuariosService.findAll());
        model.put("comentarios", comentarioService.findAll());
        model.put("respuestas", respuesta);
        model.put("titulo", "Formulario de respuestas");
        return "formRespuestas";
    }
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Respuesta respuesta, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Error al crear la respuesta");
            return "formRespuestas";
        }
        respuestaService.save(respuesta);
        return "redirect:/respuestas/listar";
    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable Long id,Map<String,Object>model){
        Respuesta respuesta = null;
        if(id>0){
            respuesta = respuestaService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("respuestas", respuesta);
        model.put("usuarios", usuariosService.findAll());
        model.put("comentarios", comentarioService.findAll());
        model.put("titulo", "Editar respuesta");
        return "formRespuestas";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        if(id>0)
            respuestaService.delete(id);
        return "redirect:/respuestas/listar";
    }
}
