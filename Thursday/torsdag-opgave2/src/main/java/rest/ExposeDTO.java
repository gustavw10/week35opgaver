package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class ExposeDTO {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static String getEmployeeById(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        
        EmployeeDTO employee = facade.DTOgetEmployeeById(id);
        return new Gson().toJson(employee);
    }
    
    @Path("/em/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static String getEmployeeByName(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        
        List<EmployeeDTO> employee = facade.DTOgetEmployeesByName(name);
        return new Gson().toJson(employee);
    }
   
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        EntityManager em = emf.createEntityManager();
    try{
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        List<EmployeeDTO> list = facade.DTOgetAllEmployees();
        return new Gson().toJson(list);
    } finally {
        em.close();
    }
    }
    
    @Path("/highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        EntityManager em = emf.createEntityManager();
    try{
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        EmployeeDTO employee = facade.getHighestPaid();
        
        return new Gson().toJson(employee);
    } finally {
        em.close();
    }
    }
}
