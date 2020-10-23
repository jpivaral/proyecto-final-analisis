package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Pais;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class PaisRepository extends AbstractEntityRepository<Pais, Integer>{
    
    public abstract List<Pais> findByIdPais(Integer idPais);    
    
}
