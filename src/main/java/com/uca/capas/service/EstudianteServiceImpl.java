package com.uca.capas.service;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.dto.EstudianteDTO;
import com.uca.capas.repositories.EstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    EstudianteRepo estudianteRepo;
    //EstudianteDAO estudianteDAO;

    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        //return estudianteRepo.findAll();
        return estudianteRepo.showAll();
    }

    @Override
    public Estudiante findOne(Integer code) throws DataAccessException {
        return estudianteRepo.findByCodigoEstudiante(code);
    }

    @Override
    public void save(Estudiante estudiante) throws DataAccessException {
        estudianteRepo.save(estudiante);
    }

    @Override
    public void delete(Integer code) throws DataAccessException {
        estudianteRepo.deleteById(code);
    }

    @Override
    public List<Estudiante> filterBy(String cadena) throws DataAccessException {
        //return estudianteRepo.findByNombre(cadena);
        return estudianteRepo.showByName(cadena);
    }

    @Override
    public List<Estudiante> startsWith(String cadena) throws DataAccessException {
        return estudianteRepo.findByApellidoStartingWith(cadena);
    }

    @Override
    public List<EstudianteDTO> dtoPrueba() throws DataAccessException {
        List<EstudianteDTO> estudiantes = estudianteRepo.pruebaDTO().stream().map(obj -> {
            EstudianteDTO estudiante = new EstudianteDTO();
            estudiante.setNombre(obj[0].toString());
            estudiante.setApellido(obj[1].toString());
            return estudiante;
        }).collect(Collectors.toList());
        return estudiantes;
    }

}

