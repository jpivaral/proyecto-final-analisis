package com.umg.proyectocovid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author jocpi
 */
@Entity
@Table(name = "persona")
@Data
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSONA")
    private Integer idPersona;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "APELLIDO")
    private String apellido;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TELEFONO")
    private Long telefono;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GENERO")
    private String genero;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSONA")
    private List<IdentificacionPersona> identificaciones;
    
    public String getFullGenero(){
        return this.getGenero().equalsIgnoreCase("M") ? "MASCULINO" : "FEMENINO";
    }
    
    public String getIdentificacion(){
        return identificaciones.stream()
                .findFirst()
                .orElse(new IdentificacionPersona()).getIdentificacion();
    }

}
