package com.umg.proyectocovid.web;

import com.umg.proyectocovid.model.Departamento;
import com.umg.proyectocovid.model.Municipio;
import com.umg.proyectocovid.repository.DepartamentoRepository;
import com.umg.proyectocovid.repository.MunicipioRepository;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jocpi
 */
@Path("paises/{idPais}/departamentos/{idDepartamento}/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioEndpoint {
    
    @Inject
    private MunicipioRepository municipioRepository;
    
    @GET
    public Response findMunicipioByDepartamento(@PathParam("idDepartamento") Integer idDepartamento){
        return Response
                .ok(municipioRepository.findByIdDepartamento(idDepartamento))
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response findMunicipioById(@PathParam("idDepartamento") Integer idDepartamento,
            @PathParam("id") Integer id){
        return Response
                .ok(municipioRepository.findBy(id))
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteMunicipioById(@PathParam("idDepartamento") Integer idDepartamento,
            @PathParam("id") Integer id){
        var municipio = municipioRepository.findBy(id);
        municipioRepository.attachAndRemove(municipio);
        return Response
                .ok()
                .build();
    }
    
    @POST
    public Response createMunicipio(@PathParam("idDepartamento") Integer idDepartamento,
            Municipio municipio){
        municipio.setIdDepartamento(idDepartamento);
        return Response
                .ok(municipioRepository.saveAndFlush(municipio))
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Response modifyMunicipioById(@PathParam("idDepartamento") Integer idDepartamento,
            @PathParam("id") Integer id, Municipio municipio){
        municipio.setIdMunicipio(id);
        if(municipio.getIdDepartamento()== null){
            municipio.setIdDepartamento(idDepartamento);
        }
        return Response
                .ok(municipioRepository.save(municipio))
                .build();
    }
}
