package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.TipoIdentificacionPersona;
import com.umg.proyectocovid.repository.TipoIdentificacionRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class TipoIdentificacionService implements Serializable{
    
    private Integer idTipoIdentificacion;
    private String tipoIdentificacion;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private TipoIdentificacionRepository tipoIdentificacionRepository;
    
    public List<TipoIdentificacionPersona> getTipoIdentificaciones(){
        return tipoIdentificacionRepository.findAll();
    } 
    
    public Map<Integer, String> getSetTipoIdentificaciones() {
        return tipoIdentificacionRepository.findAll()
                .stream()
                .collect(Collectors.toMap(TipoIdentificacionPersona::getIdTipo, TipoIdentificacionPersona::getTipoIdentificacion));
    }
    
    public String save(){
        var tipoIdentificacionPersona = new TipoIdentificacionPersona();
        if(this.getIdTipoIdentificacion()!= null){
            tipoIdentificacionPersona.setIdTipo(getIdTipoIdentificacion());
        }        
        tipoIdentificacionPersona.setTipoIdentificacion(this.getTipoIdentificacion());
        tipoIdentificacionRepository.save(tipoIdentificacionPersona);
        return "./list.xhtml?faces-redirect=true";
    }
    
    public void delete(Integer id){
         var tipoIdentificacion = tipoIdentificacionRepository.findBy(id);
        tipoIdentificacionRepository.attachAndRemove(tipoIdentificacion);
    }
    
    public String redirect(Integer id){
        var tipoIdentificacion = tipoIdentificacionRepository.findBy(id);
        this.setIdTipoIdentificacion(tipoIdentificacion.getIdTipo());
        this.setTipoIdentificacion(tipoIdentificacion.getTipoIdentificacion());
        return "./save.xhtml?faces-redirect=true";
    }
    
    public String redirect(){
        this.setIdTipoIdentificacion(null);
        this.setTipoIdentificacion(null);
        return "./save.xhtml?faces-redirect=true";
    }
}
