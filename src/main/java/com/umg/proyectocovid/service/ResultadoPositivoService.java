package com.umg.proyectocovid.service;

import com.umg.proyectocovid.model.ConteoPrueba;
import com.umg.proyectocovid.repository.PruebaCovidRepository;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
@Named
@Data
public class ResultadoPositivoService implements Serializable {

    @Inject
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PruebaCovidRepository pruebaCovidRepository;

    public List<ConteoPrueba> getPruebasPositivas() {
        var result = pruebaCovidRepository.findResults();
        return result.stream()
                .map(p -> {
                    Object[] fields = (Object[]) p;
                    return new ConteoPrueba(fields[0].toString(), fields[1].toString(),
                            fields[2].toString(), Long.parseLong(fields[3].toString()));
                })
                .collect(Collectors.toList());
    }

    public String setCellStyle(Long value) {
        if (value >= 5) {
            return "FAILURE";
        } else if (value >= 3) {
            return "WARNING";
        } else {
            return "FINE";
        }
    }
}
