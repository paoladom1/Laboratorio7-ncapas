package com.uca.capas.dao;

import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO {
    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.estudiante");
        Query query = entityManager.createNativeQuery(sb.toString(), Estudiante.class);
        List<Estudiante> resultset = query.getResultList();

        return resultset;
    }

    @Override
    public Estudiante findOne(Integer code) throws DataAccessException {
        Estudiante estudiante = entityManager.find(Estudiante.class, code);
        return estudiante;
    }

    @Override
    @Transactional
    public void save(Estudiante estudiante) throws DataAccessException {
        try {
            if(estudiante.getCodigoEstudiante() == null) {
                entityManager.persist(estudiante);
            } else {
                entityManager.merge(estudiante);
                entityManager.flush();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void delete(Integer codigoEstudiante) throws DataAccessException {
        Estudiante estudiante = entityManager.find(Estudiante.class, codigoEstudiante);
        entityManager.remove(estudiante);
    }
}
