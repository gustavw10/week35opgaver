/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Point;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Gustav
 */
@Path("point")
public class PointResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public PointResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        //TODO return proper representation object
        return "HelloXXXXX";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPoints(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Point points = new Point();
        System.out.println("lol");
        String jsout = gson.toJson(points);
        return Response.ok().entity(jsout).build();
    }
    
    @GET
    @Path("/two")
    @Produces("text/plain")
    public String getTwo(){
        return "You have arrived, EKO";
    }
   
    @GET
    @Path("/PathDemo")
    @Produces("text/plain")
    public String getPathDemo(@PathParam("id") int id){
        return "" + id;
    }
    
    @Path("/users/{username}")
    public class UserResource {
    @GET
    @Produces("text/xml")
    public String getUser(@PathParam("username") String userName){         
    return "Welcome " + userName;
  }
}


    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
