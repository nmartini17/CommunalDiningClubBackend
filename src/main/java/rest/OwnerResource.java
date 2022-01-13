package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTO;
import entities.Owner;
import facades.FacadeExample;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.InvalidClassException;
import java.util.List;

@Path("owner")
public class OwnerResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final OwnerFacade FACADE =  OwnerFacade.getOwnerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("all")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllOwners() {
        List<OwnerDTO> ownerList = FACADE.getAll();
        return GSON.toJson(ownerList);
    }

    @Path("boat/{boatName}")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBoatOwner(@PathParam("boatName") String boatName) {
        List<OwnerDTO> odtoList = FACADE.showAllBoatOwners(boatName);
        return GSON.toJson(odtoList);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addOwnerToBoat(String boat) {
        OwnerDTO odto = GSON.fromJson(boat, OwnerDTO.class);
        OwnerDTO newodto = FACADE.addOwnerToBoat(odto);
        return GSON.toJson(newodto);
    }
}
