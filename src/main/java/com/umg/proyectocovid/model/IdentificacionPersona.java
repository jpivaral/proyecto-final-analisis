package com.umg.proyectocovid.model;

import java.io.Serializable;
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
@Table(name = "identificacion_persona")
@Data
public class IdentificacionPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_IDENTIFICACION")
    private Integer idIdentificacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    
    @Column(name = "ID_PAIS")
    private Integer idPais;
    
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    @ManyToOne(optional = false)
    private TipoIdentificacionPersona tipoIdentificacion;   
    
    @Column(name = "ID_PERSONA")
    private Integer idPersona;
   
}
