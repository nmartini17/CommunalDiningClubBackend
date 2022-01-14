package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnereventDTO;
import dtos.MemberDTO;
import dtos.UserDTO;
import entities.Member;
import entities.User;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import facades.MemberFacade;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("member")
public class MemberResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MemberFacade FACADE =  MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("all")
    @GET
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        List<MemberDTO> memberDTOList = FACADE.getAllMembers();
        return GSON.toJson(memberDTOList);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addMemberToAssignment(String assignment) {
        MemberDTO mDTO = GSON.fromJson(assignment, MemberDTO.class);
        MemberDTO newMDTO = FACADE.addMemberToAssignment(mDTO);
        return GSON.toJson(newMDTO);
    }

    @Path("{id}")
    @GET
    @RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAccountAmount(@PathParam("id") int id) throws EntityNotFoundException, NotFoundException {
         MemberDTO mDTO = FACADE.getAccountStatus(id);
        return GSON.toJson(mDTO);
    }

    @Path("create")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"admin"})
    public String createMember(String member) throws InvalidInputException {
        MemberDTO mDTO = GSON.fromJson(member, MemberDTO.class);
        MemberDTO newmDTO = FACADE.createMember(mDTO);
        return GSON.toJson(newmDTO);
    }
}
