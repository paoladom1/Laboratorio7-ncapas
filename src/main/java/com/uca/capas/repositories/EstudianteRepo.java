package com.uca.capas.repositories;

import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

    public List<Estudiante> findByNombre(String cadena) throws DataAccessException;

    public List<Estudiante> findByApellidoStartingWith(String cadena) throws DataAccessException;

    public Estudiante findByCodigoEstudiante(Integer codigo) throws DataAccessException;

    @Query(nativeQuery = true, value = "select * from public.estudiante")
    public List<Estudiante> showAll() throws DataAccessException;

    @Query(nativeQuery = true, value = "select * from public.estudiante where nombre = :cadena")
    public List<Estudiante> showByName(String cadena) throws DataAccessException;

    @Query(nativeQuery = true, value = "select nombre, apellido from public.estudiante")
    public List<Object[]> pruebaDTO() throws DataAccessException;

}
