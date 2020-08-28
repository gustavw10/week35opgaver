/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("animals_db")
public class AnimalsFromDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsFromDB
     */
    public AnimalsFromDB() {
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJason() {
        //TODO return proper representation object
        return "EKO----";
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
    
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        for(int i = 0; i > animals.size(); i++){
              System.out.println(animals.get(i).getId());
          }
        
    }
    
     
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type){
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
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
    public String getAnimalById(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        Animal returnAnimal = em.find(Animal.class, id);
        
        return new Gson().toJson(returnAnimal);
    }
    
    @Path("randomanimal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Animal c", Long.class);
        long animalCount = query.getSingleResult();
        
        Random random = new Random();
        int number = random.nextInt((int)animalCount);
        
        Query selectQuery = em.createQuery("select a from Animal a");
        selectQuery.setFirstResult(number);
        selectQuery.setMaxResults(1);
        Animal returnAnimal = (Animal) selectQuery.getSingleResult();
  
        return new Gson().toJson(returnAnimal);
    }
}
