package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnereventDTO;
import dtos.MemberDTO;
import entities.Dinnerevent;
import errorhandling.InvalidInputException;
import facades.DinnereventFacade;
import facades.MemberFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("dinnerevent")
public class DinnereventResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DinnereventFacade FACADE =  DinnereventFacade.getDinnereventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("all")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDinners() {
        List<DinnereventDTO> dinnereventDTOList = FACADE.getAllEvents();
        return GSON.toJson(dinnereventDTOList);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    //@RolesAllowed({"admin"})
    public String createEvent(String event) throws InvalidInputException {
        DinnereventDTO dDTO = GSON.fromJson(event, DinnereventDTO.class);
        DinnereventDTO newdDTO = FACADE.createEvent(dDTO);
        return GSON.toJson(newdDTO);
    }

    @Path("delete/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id, String event) throws EntityNotFoundException {
        return FACADE.deleteEvent(id);
    }

    @Path("edit/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String editEvent(@PathParam("id")int id, String event)throws EntityNotFoundException{
        DinnereventDTO dDTO =  GSON.fromJson(event, DinnereventDTO.class);
        dDTO.setId(id);
        return GSON.toJson(FACADE.editEvent(dDTO));
    }

}
