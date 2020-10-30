package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Persona;
import com.umg.proyectocovid.model.Usuario;
import com.umg.proyectocovid.repository.RolRepository;
import com.umg.proyectocovid.repository.UsuarioRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

@RequestScoped
public class UsuarioService {
    
    @Inject
    private UsuarioRepository usuarioRepository;
    
    @Inject
    private RolRepository rolRepository;
    
    public Usuario saveUsuarioFromPersona(Persona persona){
        var usuario = usuarioRepository.findByPersona(persona).orElse(new Usuario());
        if(usuario.getIdUsuario() == null) {
              var rol = rolRepository.findByRol("USER");
              usuario.setRol(rol);
              usuario.setPersona(persona);
              usuario.setPassword(this.genarateInialtPassword());
              usuario.setUsuario(usuario.generateUser());
              return usuarioRepository.save(usuario);
        } else {
            return usuario;
        }    
    }
    
    public String genarateInialtPassword(){
        var initialPassword = "Abc123456";
        return BCrypt.hashpw(initialPassword, BCrypt.gensalt());
    }
    
    public Usuario findByIdUsuario(Integer id){
        return usuarioRepository.findBy(id);
    }
    
    public boolean validateCredentials(String usuario, String password){
        var usuarioDb = usuarioRepository.findByUsuario(usuario);
        if(usuarioDb.isPresent()){
            return BCrypt.checkpw(password, usuarioDb.get().getPassword());
        } else {
            return false;
        }
    }
}