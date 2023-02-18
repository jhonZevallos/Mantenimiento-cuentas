package com.nttdata.bc.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.nttdata.bc.models.Client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/clients")
@RegisterRestClient
public interface IClientRestClient {

    @GET
    @Path("/{id}")
    Client fintById(@PathParam("id") Integer id);

    @GET
    @Path("/findByTypeAndNumDoc/{documentIdentityType}/{documentIdentity}")
    Client findByTypeAndNumDoc(@PathParam("documentIdentityType") String documentIdentityType,
            @PathParam("documentIdentity") String documentIdentity);

}
