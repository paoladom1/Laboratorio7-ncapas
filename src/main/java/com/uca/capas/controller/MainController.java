package com.uca.capas.controller;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.dto.EstudianteDTO;
import com.uca.capas.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private EstudianteService estudianteService;

    @RequestMapping("/estudiante")
    public ModelAndView initMain() {
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;

        try {
            estudiantes = estudianteService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/editarEstudiante")
    public ModelAndView editar(@RequestParam(value = "codigo") int id) {
        ModelAndView mav = new ModelAndView();

        Estudiante estudiante = estudianteService.findOne(id);

        mav.addObject("estudiante", estudiante);
        mav.setViewName("editarEstudiante");
        return mav;
    }

    @RequestMapping(value = "/mostrarEstudiante", method = RequestMethod.POST)
    public ModelAndView findOne(@RequestParam(value = "codigo") int id) {
        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = estudianteService.findOne(id);

        mav.addObject("estudiante", estudiante);
        mav.setViewName("estudiante");

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante, BindingResult br) {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            mav.setViewName("agregarEstudiante");
        } else {
            estudianteService.save(estudiante);
            List<Estudiante> estudiantes = estudianteService.findAll();


            mav.addObject("estudiantes", estudiantes);
            mav.setViewName("listaEstudiantes");
        }

        return mav;
    }

    @PostMapping("/mostrarDTO")
    public ModelAndView mostrarDTO() {
        ModelAndView mav = new ModelAndView();
        List<EstudianteDTO> estudiantes = null;

        try {
            estudiantes = estudianteService.dtoPrueba();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("muestraDTO");
        mav.addObject("estudiantes", estudiantes);

        return mav;
    }

    @PostMapping("/filtrar")
    public ModelAndView filtro(@RequestParam(value = "nombre") String cadena) {
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;

        try {
            estudiantes = estudianteService.filterBy(cadena);
            //estudiantes = estudianteService.startsWith(cadena);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("listaEstudiantes");
        mav.addObject("estudiantes", estudiantes);

        return mav;
    }

    @RequestMapping(value = "/borrarEstudiante", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "codigo") int id) {
        ModelAndView mav = new ModelAndView();
        estudianteService.delete(id);
        List<Estudiante> estudiantes = null;

        try {
            estudiantes = estudianteService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("listaEstudiantes");

        return mav;
    }

    @GetMapping("/insertarEstudiante")
    public ModelAndView inicio() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("estudiante", new Estudiante());
        mav.setViewName("agregarEstudiante");

        return mav;
    }

    @RequestMapping("/")
    public ModelAndView Main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }

}
