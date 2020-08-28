package facades;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import rest.ExposeDTO;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
//
//    private EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
    
    public Employee getEmployeeById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            return employee;
        }finally {
            em.close();
        }
    }
    
    public EmployeeDTO DTOgetEmployeeById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            return employeeDTO;
        }finally {
            em.close();
        }
    }

    
    public List<Employee> getEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> DTOgetEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            List<Employee> eList = query.getResultList();
            List<EmployeeDTO> eListDTO = null;
            for (int i = 0; i > eList.size(); i++){
                EmployeeDTO e = new EmployeeDTO(eList.get(i));
                eListDTO.add(e);
            }
            return eListDTO;
        }finally {
            em.close();
        }
    }
    
     public List<EmployeeDTO> DTOgetAllEmployees(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e", Employee.class);
            List<Employee> eList = query.getResultList();
            List<EmployeeDTO> eListDTO = null;
            for (int i = 0; i > eList.size(); i++){
                EmployeeDTO e = new EmployeeDTO(eList.get(i));
                eListDTO.add(e);
            }
            return eListDTO;
        }finally {
            em.close();
        }
    }
    
    public Employee addEmployee(String name, String address, int salary){
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    
    public EmployeeDTO getHighestPaid(){
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("Select MAX(e.salary) FROM Employee e");
        BigDecimal result = (BigDecimal)query.getSingleResult();
            
        Query query2 = em.createQuery("Select e FROM Employee e WHERE e.salary = :salary");
        query2.setParameter("salary", result);
        Employee employee = (Employee)query2.getSingleResult();
        
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            return employeeDTO;
    }


}
