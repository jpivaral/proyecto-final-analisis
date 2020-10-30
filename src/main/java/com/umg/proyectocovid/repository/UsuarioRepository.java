package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Persona;
import com.umg.proyectocovid.model.Usuario;
import java.util.Optional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

/**
 *
 * @author jocpi
 */
public abstract class UsuarioRepository extends AbstractEntityRepository<Usuario, Integer>{
    
    public abstract Optional<Usuario> findByPersona(Persona persona);
    
    public abstract Optional<Usuario> findByUsuario(String usuario);
}
