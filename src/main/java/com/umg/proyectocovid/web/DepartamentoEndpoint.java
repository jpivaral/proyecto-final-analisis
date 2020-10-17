package com.umg.proyectocovid.web;

import com.umg.proyectocovid.model.Departamento;
import com.umg.proyectocovid.repository.DepartamentoRepository;
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
@Path("paises/{idPais}/departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoEndpoint {
    
    @Inject
    private DepartamentoRepository departamentoRepository;
    
    @GET
    public Response findDepartamentoByPais(@PathParam("idPais") Integer idPais){
        return Response
                .ok(departamentoRepository.findByIdPais(idPais))
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response findDepartamentoById(@PathParam("idPais") Integer idPais,
            @PathParam("id") Integer id){
        return Response
                .ok(departamentoRepository.findBy(id))
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteDepartamentoById(@PathParam("idPais") Integer idPais,
            @PathParam("id") Integer id){
        var departamento = departamentoRepository.findBy(id);
        departamentoRepository.attachAndRemove(departamento);
        return Response
                .ok()
                .build();
    }
    
    @POST
    public Response createDepartamento(@PathParam("idPais") Integer idPais,
            Departamento departamento){
        departamento.setIdPais(idPais);
        return Response
                .ok(departamentoRepository.saveAndFlush(departamento))
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Response modifyDepartamentoById(@PathParam("idPais") Integer idPais,
            @PathParam("id") Integer id, Departamento departamento){
        departamento.setIdDepartamento(id);
        if(departamento.getIdPais() == null){
            departamento.setIdPais(idPais);
        }
        return Response
                .ok(departamentoRepository.save(departamento))
                .build();
    }
}
