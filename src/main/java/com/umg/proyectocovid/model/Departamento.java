package com.umg.proyectocovid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jocpi
 */
@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DEPARTAMENTO")
    private List<Municipio> municipios;   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPARTAMENTO")
    private Integer idDepartamento;
    @JsonIgnore
    @Column(name = "ID_PAIS")
    private Integer idPais;
    
}
