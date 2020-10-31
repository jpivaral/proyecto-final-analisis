package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.Hospital;
import com.umg.proyectocovid.model.Persona;
import com.umg.proyectocovid.model.PruebaCovid;
import com.umg.proyectocovid.repository.HospitalRepository;
import com.umg.proyectocovid.repository.IdentificacionPersonaRepository;
import com.umg.proyectocovid.repository.PersonaRepository;
import com.umg.proyectocovid.repository.PruebaCovidRepository;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class PruebaCovidService implements Serializable {

    private Integer idPrueba;
    private Date fecha;
    private String resultado = "PENDIENTE";
    private Integer idPersona;
    private Integer idHospital;
    private String identificacion;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PruebaCovidRepository pruebaCovidRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private HospitalRepository hospitalRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PersonaRepository personaRepository;

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private IdentificacionPersonaRepository identificacionRepository;

    public void buscar() {
    }

    public List<PruebaCovid> getPruebas() {
        if (this.getIdentificacion() == null) {
            return null;
        } else {
            var opIdentificacion = identificacionRepository.findByIdentificacion(this.getIdentificacion());
            var pruebasCovid = new ArrayList<PruebaCovid>();
            opIdentificacion.ifPresent(i -> {
                var persona = new Persona();
                persona.setIdPersona(i.getIdPersona());
                pruebasCovid.addAll(
                        pruebaCovidRepository.findByPersona(persona)
                );
            });
            return pruebasCovid;
        }
    }

    public String save() {
        var pruebaCovid = new PruebaCovid();
        var persona = new Persona();
        var hospital = new Hospital();
        if (this.getIdPrueba() != null) {
            pruebaCovid.setIdPrueba(this.getIdPrueba());
            var pruebaCovidDb = pruebaCovidRepository.findBy(this.getIdPrueba());
            persona = pruebaCovidDb.getPersona();
            hospital = pruebaCovidDb.getHospital();
        }

        if (persona.getIdPersona() == null) {
            var opIdentificacion = identificacionRepository.findByIdentificacion(this.getIdentificacion());
            if (opIdentificacion.isPresent()) {
                persona = personaRepository.findBy(opIdentificacion.get().getIdPersona());
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Identificacion no encontrada en el sistema",
                                "Identificacion no encontrada, debe agregar primero el paciente al sistema"));
                return "./save.xhtml?faces-redirect=true";
            }
        }
        if (hospital.getIdHospital() == null ){
            hospital = hospitalRepository.findBy(this.getIdHospital());
        }
        var date = this.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        pruebaCovid.setFecha(date);
        pruebaCovid.setResultado(this.getResultado());
        pruebaCovid.setPersona(persona);
        pruebaCovid.setHospital(hospital);
        pruebaCovidRepository.save(pruebaCovid);
        return "./list.xhtml?faces-redirect=true";
    }

    public void delete(Integer id) {
        var pruebaCovid = pruebaCovidRepository.findBy(id);
        pruebaCovidRepository.attachAndRemove(pruebaCovid);
    }

    public String redirect(Integer id) {
        var pruebaCovid = pruebaCovidRepository.findBy(id);
        this.setIdPrueba(pruebaCovid.getIdPrueba());
        this.setFecha(Date.from(pruebaCovid.getFecha().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
        );
        this.setIdHospital(pruebaCovid.getHospital().getIdHospital());
        this.setIdentificacion(pruebaCovid.getPersona().getIdentificacion());
        this.setResultado(pruebaCovid.getResultado());
        return "./save.xhtml?faces-redirect=true";
    }

    public String redirect() {
        this.setIdPrueba(null);
        this.setFecha(null);
        this.setResultado("PENDIENTE");
        this.setIdHospital(null);
        this.setIdPersona(null);
        this.setIdentificacion(null);
        return "./save.xhtml?faces-redirect=true";
    }
}
