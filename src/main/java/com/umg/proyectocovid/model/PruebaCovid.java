package com.umg.proyectocovid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author jocpi
 */
@Entity
@Table(name = "prueba_covid")
@Data
public class PruebaCovid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRUEBA")
    private Integer idPrueba;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RESULTADO")
    private String resultado;
    
    @ManyToOne
    @JoinColumn(name="ID_PERSONA")
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name="ID_HOSPITAL")    
    private Hospital hospital;
    
}
