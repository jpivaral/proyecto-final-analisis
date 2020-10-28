package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Departamento;
import com.umg.proyectocovid.model.Pais;
import com.umg.proyectocovid.repository.DepartamentoRepository;
import com.umg.proyectocovid.repository.PaisRepository;
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
public class DepartamentoService implements Serializable {

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private DepartamentoRepository departamentoRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PaisRepository paisRepository;

    private Integer idPais;
    private Integer idDepartamento;
    private String nombreDepartamento;

    public Map<Integer, String> getPaises() {
        return paisRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Pais::getIdPais, Pais::getPais));
    }

    public List<Departamento> getDepartamentos() {
        return idPais != null ? departamentoRepository.findByIdPais(this.idPais) : null;
    }

    public void onPaisChange() {
        //this.getDepartamentos();
    }

    public String save() {
        var departamento = new Departamento();
        if (this.getIdDepartamento() != null) {
            departamento.setIdDepartamento(idDepartamento);
        }
        departamento.setIdPais(idPais);
        departamento.setDepartamento(nombreDepartamento);
        departamentoRepository.save(departamento);
        return "./list.xhtml?faces-redirect=true";
    }

    public void delete(Integer id) {
        var departamento = departamentoRepository.findBy(id);
        departamentoRepository.attachAndRemove(departamento);
    }

    public String redirect(Integer id) {
        var departamento = departamentoRepository.findBy(id);
        this.setIdPais(this.getIdPais());
        this.setIdDepartamento(id);
        this.setNombreDepartamento(departamento.getDepartamento());
        return "./save.xhtml?faces-redirect=true";
    }

    public String redirect() {
        this.setIdPais(null);
        this.setIdDepartamento(null);
        this.setNombreDepartamento(null);
        return "./save.xhtml?faces-redirect=true";
    }
}
