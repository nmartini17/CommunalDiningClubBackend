package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import facades.BoatFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("boat")
public class BoatResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final BoatFacade FACADE = BoatFacade.getBoatFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("harbour/{harbourName}")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String showBoatsFromHarbour(@PathParam("harbourName") String harbourName) {
        List<BoatDTO> bdtoList = FACADE.showBoatsFromHarbour(harbourName);
        return GSON.toJson(bdtoList);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createBoat(String boat) {
        BoatDTO bdto = GSON.fromJson(boat, BoatDTO.class);
        BoatDTO newbdto = FACADE.createBoat(bdto);
        return GSON.toJson(newbdto);
    }

    @Path("delete/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id, String boat) throws EntityNotFoundException {
        return FACADE.deleteBoat(id);
    }

    @Path("edit/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String editBoat(@PathParam("id") int id, String boat) throws EntityNotFoundException {
        BoatDTO bDTO = GSON.fromJson(boat, BoatDTO.class);
        bDTO.setID(id);
        return GSON.toJson(FACADE.editBoat(bDTO));
    }
}
