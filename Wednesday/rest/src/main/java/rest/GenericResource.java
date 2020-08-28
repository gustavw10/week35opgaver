/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Gustav
 */
@Path("generic")
public class GenericResource {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJason() {
        //TODO return proper representation object
        return "GENERIC";
    }

    @Path("animalsEKO")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        return new Gson().toJson(animals);
    } finally {
        em.close();
    }
  }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalType(@PathParam("type") String type){
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal where a.type = :type", Animal.class);
            query.setParameter("type", type);
            Animal a = query.getSingleResult();
            return new Gson().toJson(a);
        } catch (Exception e ){
            return "You made an error.";
        }
        finally {
            em.close();
        }
    }
    
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        Animal returnAnimal = em.find(Animal.class, id);
        
        return new Gson().toJson(returnAnimal);
    }
    
}
