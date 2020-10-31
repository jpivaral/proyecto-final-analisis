package com.umg.proyectocovid.repository;

import com.umg.proyectocovid.model.Persona;
import com.umg.proyectocovid.model.PruebaCovid;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;

/**
 *
 * @author jocpi
 */
public abstract class PruebaCovidRepository extends AbstractEntityRepository<PruebaCovid, Integer> {

    public abstract List<PruebaCovid> findByPersona(Persona persona);

    @Query(isNative = true, value = "select p.PAIS pais, d.DEPARTAMENTO departamento, m.MUNICIPIO municipio, count(1) cantidad"
            + " from prueba_covid pr"
            + " inner join ("
            + "	SELECT "
            + "		MAX(tmp.ID_PRUEBA) ID_PRUEBA"
            + "	FROM "
            + "		prueba_covid tmp"
            + "	where "
            + "		tmp.RESULTADO = 'POSITIVO'"
            + "	GROUP BY "
            + "		tmp.ID_PERSONA) as positivos"
            + " on pr.ID_PRUEBA = positivos.ID_PRUEBA"
            + " inner join hospital h on pr.ID_HOSPITAL = h.ID_HOSPITAL"
            + " inner join direccion dir on h.ID_DIRECCION = dir.ID_DIRECCION "
            + " inner join municipio m on dir.ID_MUNICIPIO = m.ID_MUNICIPIO"
            + " inner join departamento d on m.ID_DEPARTAMENTO = d.ID_DEPARTAMENTO "
            + " inner join pais p on d.ID_PAIS = p.ID_PAIS"
            + " GROUP BY p.PAIS, d.DEPARTAMENTO, m.MUNICIPIO;")
    public abstract List<Object[]> findResults();

}
