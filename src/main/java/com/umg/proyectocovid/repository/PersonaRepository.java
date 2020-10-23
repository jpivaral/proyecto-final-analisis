package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Persona;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class PersonaRepository extends AbstractEntityRepository<Persona, Integer>{   
    
    
    public List<Persona> findAllByIdPais(Integer idPais){
        return this.findAll()
            .stream()
            .filter(p -> p.getIdentificaciones()
                    .stream()
                    .filter(i -> i.getIdPais().intValue() == idPais.intValue())
                    .count() > 0)
            .collect(Collectors.toList());
    }    
    
     public Persona findAllByIdPaisAndId(Integer idPais, Integer id){
        var persona = this.findBy(id);
        return persona != null 
                && persona.getIdentificaciones()
                    .stream()
                    .filter(i -> i.getIdPais().intValue() == idPais.intValue())
                    .count() > 0 ? persona : null;
    } 
}