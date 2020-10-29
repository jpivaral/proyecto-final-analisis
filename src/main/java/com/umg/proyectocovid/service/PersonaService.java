package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.IdentificacionPersona;
import com.umg.proyectocovid.model.Persona;
import com.umg.proyectocovid.repository.IdentificacionPersonaRepository;
import com.umg.proyectocovid.repository.PaisRepository;
import com.umg.proyectocovid.repository.PersonaRepository;
import com.umg.proyectocovid.repository.TipoIdentificacionRepository;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jocpi
 */
@Named
@SessionScoped
@Data
public class PersonaService implements Serializable {

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PaisRepository paisRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PersonaRepository personaRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private IdentificacionPersonaRepository identificacionPersonaRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    private Integer idPersona;
    private String nombre;
    private String apellido;
    private String genero;
    private Long telefono;
    private Date fechaNacimiento;
    private Integer idTipoIdentificacion;
    private Integer idPais;
    private Integer idIdentificacion;
    private String identificacion;

    public List<Persona> getPersonas() {
        return personaRepository.findAll();
    }

    public Map<String, String> getGeneros() {
        var generos = new HashMap<String, String>();
        generos.put("M", "MASCULINO");
        generos.put("F", "FEMENINO");
        return generos;
    }

    public String save() {
        var persona = new Persona();
        if (this.getIdPersona() != null) {
            persona.setIdPersona(this.getIdPersona());
            var personaDB = personaRepository.findBy(idPersona);
            persona.setIdentificaciones(personaDB.getIdentificaciones());
        }
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setTelefono(telefono);
        var date = fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        persona.setFechaNacimiento(date);
        persona.setGenero(genero);
        var identificacionPersona = persona.getIdentificaciones().stream()
                .findFirst()
                .orElse(new IdentificacionPersona());                
        identificacionPersona.setIdPersona(persona.getIdPersona());
        identificacionPersona.setIdPais(idPais);
        var tipoIdentificacion = tipoIdentificacionRepository.findBy(idTipoIdentificacion);
        identificacionPersona.setTipoIdentificacion(tipoIdentificacion);
        identificacionPersona.setIdentificacion(identificacion);
        persona.setIdentificaciones( Collections.singletonList(identificacionPersona) );
        persona = personaRepository.save(persona);
        return "./list.xhtml?faces-redirect=true";
    }

    public String redirect(Integer id) {
        var persona = personaRepository.findBy(id);
        this.setIdPersona(persona.getIdPersona());
        this.setNombre(persona.getNombre());
        this.setApellido(persona.getApellido());
        this.setGenero(persona.getGenero());
        this.setTelefono(persona.getTelefono());
        this.setFechaNacimiento(Date.from(persona.getFechaNacimiento().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
        );
        var opIdentificacion = persona.getIdentificaciones().stream().findFirst();
        opIdentificacion.ifPresent( i -> {
            this.setIdentificacion(i.getIdentificacion());
            this.setIdIdentificacion(i.getIdIdentificacion());
            this.setIdPais(i.getIdPais());
            this.setIdTipoIdentificacion(i.getTipoIdentificacion().getIdTipo());
        });        
        return "./save.xhtml?faces-redirect=true";
    }

    public String redirect() {
        this.setIdIdentificacion(null);
        this.setIdPersona(null);
        return "./save.xhtml?faces-redirect=true";
    }
    /*
    public String redirectDepartamento(Integer id){
        departamentoService.setIdPais(id);
        return "/departamento/list.xhtml?faces-redirect=true";
    }*/
}
