package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Departamento;
import com.umg.proyectocovid.model.Municipio;
import com.umg.proyectocovid.repository.DepartamentoRepository;
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
public class MunicipioService implements Serializable {

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private DepartamentoRepository departamentoRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MunicipioRepository municipioRepository;

    private Integer idPais;
    private Integer idDepartamento;
    private Integer idMunicipio;
    private String nombreMunicipio;

    public Map<Integer, String> getDepartamentos() {
        return idPais != null ?  departamentoRepository.findByIdPais(idPais)
                .stream()
                .collect(Collectors.toMap(Departamento::getIdDepartamento, Departamento::getDepartamento)) : null;
    }

    public List<Municipio> getMunicipios() {
        return idDepartamento != null ? municipioRepository.findByIdDepartamento(this.idDepartamento) : null;
    }

    public void onDepartamentoChange() {
        //this.getDepartamentos();
    }
    
    public void onPaisChange() {
        //this.getDepartamentos();
    }

    public String save() {
        var municipio = new Municipio();
        if (this.getIdMunicipio()!= null) {
            municipio.setIdMunicipio(this.getIdMunicipio());
        }
        municipio.setIdDepartamento(this.idDepartamento);
        municipio.setMunicipio(nombreMunicipio);
        municipioRepository.save(municipio);
        return "./list.xhtml?faces-redirect=true";
    }

    public void delete(Integer id) {
        var municipio = municipioRepository.findBy(id);
        municipioRepository.attachAndRemove(municipio);
    }

    public String redirect(Integer id) {
        var municipio = municipioRepository.findBy(id);
        var departamento = departamentoRepository.findBy(municipio.getIdDepartamento());
        this.setIdDepartamento(municipio.getIdDepartamento());
        this.setIdMunicipio(id);
        this.setNombreMunicipio(municipio.getMunicipio());
        this.setIdPais(departamento.getIdPais());
        return "./save.xhtml?faces-redirect=true";
    }

    public String redirect() {
        this.setIdMunicipio(null);
        this.setIdDepartamento(null);
        this.setNombreMunicipio(null);
        this.setIdPais(null);
        return "./save.xhtml?faces-redirect=true";
    }
}
