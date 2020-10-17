/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umg.proyectocovid.web;

import com.umg.proyectocovid.model.Pais;
import com.umg.proyectocovid.repository.PaisRepository;
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
@Path("paises")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaisEndpoint {
    
    @Inject
    private PaisRepository paisRepository;
    
    @GET
    public Response findAll(){
        return Response
                .ok(paisRepository.findAll())
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        return Response
                .ok(paisRepository.findBy(id))
                .build();
    }
    
    @POST
    public Response create(Pais pais){
        return Response
                .ok(paisRepository.save(pais))
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Response modify(@PathParam("id") Integer id, Pais pais){
        pais.setIdPais(id);
        return Response
                .ok(paisRepository.save(pais))
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        var pais = paisRepository.findBy(id);
        paisRepository.attachAndRemove(pais);
        return Response
                .ok()
                .build();
    }
    
}
