package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Rol;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class RolRepository extends AbstractEntityRepository<Rol, Integer>{
    
    public abstract Rol findByRol(String rol);
}
