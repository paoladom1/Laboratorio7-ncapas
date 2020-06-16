package com.uca.capas.service;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.dto.EstudianteDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EstudianteService {

    public List<Estudiante> findAll() throws DataAccessException;

    public Estudiante findOne(Integer code) throws DataAccessException;

    public void save(Estudiante estudiante) throws DataAccessException;

    public void delete(Integer codigoEstudiante) throws DataAccessException;

    public List<Estudiante> filterBy(String cadena) throws DataAccessException;

    public List<Estudiante> startsWith(String cadena) throws DataAccessException;

    public List<EstudianteDTO> dtoPrueba() throws DataAccessException;
}
