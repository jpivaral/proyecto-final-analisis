package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.IdentificacionPersona;
import java.util.Optional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class IdentificacionPersonaRepository extends AbstractEntityRepository<IdentificacionPersona, Integer>{
    
    public abstract Optional<IdentificacionPersona> findByIdentificacion(String identificacion);
}
