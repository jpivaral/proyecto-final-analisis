package com.umg.proyectocovid.web;

import com.umg.proyectocovid.repository.PersonaRepository;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jocpi
 */
@Path("paises/{idPais}/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaEndpoint {
    
    @Inject
    private PersonaRepository personaRepository;
    
    @GET
    public Response findAllByIdPais(@PathParam("idPais") Integer idPais){
        return Response
                .ok(personaRepository.findAllByIdPais(idPais))
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response findAllByIdPaisAndId(@PathParam("idPais") Integer idPais,
            @PathParam("id") Integer id){
        return Response
                .ok(personaRepository.findAllByIdPaisAndId(idPais, id))
                .build();
    }
    
}
