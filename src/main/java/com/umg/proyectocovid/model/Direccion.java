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
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author jocpi
 */
@Entity
@Table(name = "direccion")
@Data
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIRECCION")
    private Integer idDireccion;
    @Size(max = 45)
    @Column(name = "CALLE_AVENIDA")
    private String calleAvenida;
    @Size(max = 45)
    @Column(name = "NO_CASA_LOTE")
    private String noCasaLote;  
    
    @ManyToOne
    @JoinColumn(name = "ID_MUNICIPIO")
    private Municipio municipio;
    
}
