package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Pais;
import com.umg.proyectocovid.repository.PaisRepository;
import java.io.Serializable;
import java.util.List;
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
public class PaisService implements Serializable{
    
    private Integer idPais;
    private String nombrePais;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PaisRepository paisRepository;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private DepartamentoService departamentoService;
    
    public List<Pais> getPaises(){
        return paisRepository.findAll();
    } 
    
    public String save(){
        var pais = new Pais();
        if(this.getIdPais() != null){
            pais.setIdPais(idPais);
            var paisDb = paisRepository.findBy(idPais);
            pais.setDepartamentos(paisDb.getDepartamentos());
        }        
        pais.setPais(nombrePais);
        paisRepository.save(pais);
        return "./list.xhtml?faces-redirect=true";
    }
    
    public void delete(Integer id){
         var pais = paisRepository.findBy(id);
        paisRepository.attachAndRemove(pais);
    }
    
    public String redirect(Integer id){
        var pais = paisRepository.findBy(id);
        this.setIdPais(pais.getIdPais());
        this.setNombrePais(pais.getPais());
        return "./save.xhtml?faces-redirect=true";
    }
    
    public String redirect(){
        this.setIdPais(null);
        this.setNombrePais(null);
        return "./save.xhtml?faces-redirect=true";
    }
    
    public String redirectDepartamento(Integer id){
        departamentoService.setIdPais(id);
        return "/departamento/list.xhtml?faces-redirect=true";
    }
}
