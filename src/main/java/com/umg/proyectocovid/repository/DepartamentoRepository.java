package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Departamento;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class DepartamentoRepository extends AbstractEntityRepository<Departamento, Integer>{
    
    public abstract List<Departamento> findByIdPais(Integer idPais);
}