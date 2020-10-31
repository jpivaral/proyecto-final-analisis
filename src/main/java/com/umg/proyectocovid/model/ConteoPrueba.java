package com.umg.proyectocovid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author jocpi
 */
@Data
@AllArgsConstructor
public class ConteoPrueba {
    
    private String pais;
    private String departamento;
    private String municipio;
    private Long cantidad;
    
}
