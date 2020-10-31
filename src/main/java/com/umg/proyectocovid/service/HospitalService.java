package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Direccion;
import com.umg.proyectocovid.model.Hospital;
import com.umg.proyectocovid.repository.DireccionRepository;
import com.umg.proyectocovid.repository.HospitalRepository;
import com.umg.proyectocovid.repository.MunicipioRepository;
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
public class HospitalService implements Serializable{
    
    private Integer idHospital;
    private String hospital;
    private Integer idPais;
    private Integer idDepartamento;
    private Integer idMunicipio;
    private String casaOLote;
    private String avenidaYCalle;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private HospitalRepository hospitalRepository;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private DireccionRepository direccionRepository;
    
    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MunicipioRepository municipioRepository;
    
    public List<Hospital> getHospitales(){
        return hospitalRepository.findAll();
    } 
    
    public Map<Integer, String> getSetHospitales() {
        return hospitalRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Hospital::getIdHospital, Hospital::getHospital));
    }
    public String save(){
        var hospital = new Hospital();
        var direccion = new Direccion();
        if(this.getIdHospital()!= null){
            hospital.setIdHospital(this.getIdHospital());
            var hospitalDb = hospitalRepository.findBy(this.getIdHospital());
            direccion = hospitalDb.getDireccion();
        }
        
        var municipio = municipioRepository.findBy(this.getIdMunicipio());
        direccion.setMunicipio(municipio);
        direccion.setCalleAvenida(this.getAvenidaYCalle());
        direccion.setNoCasaLote(this.getCasaOLote());
        direccion = direccionRepository.save(direccion);
        hospital.setHospital(this.getHospital());
        hospital.setDireccion(direccion);
        hospitalRepository.save(hospital);
        return "./list.xhtml?faces-redirect=true";
    }
    
    public void delete(Integer id){
         var hospital = hospitalRepository.findBy(id);
        hospitalRepository.attachAndRemove(hospital);
    }
    
    public String redirect(Integer id){
        var hospital = hospitalRepository.findBy(id);
        this.setIdHospital(hospital.getIdHospital());
        this.setHospital(hospital.getHospital());
        this.setAvenidaYCalle(hospital.getDireccion().getCalleAvenida());
        this.setCasaOLote(hospital.getDireccion().getCalleAvenida());
        this.setIdDepartamento(hospital.getDireccion().getMunicipio().getIdDepartamento());
        return "./save.xhtml?faces-redirect=true";
    }
    
    public String redirect(){
        this.setIdHospital(null);
        this.setHospital(null);
        return "./save.xhtml?faces-redirect=true";
    }
}
