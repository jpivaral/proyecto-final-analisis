package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Departamento;
import com.umg.proyectocovid.model.Municipio;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class MunicipioRepository extends AbstractEntityRepository<Municipio, Integer>{
    
    public abstract List<Municipio> findByIdDepartamento(Integer idDepartamento);
}