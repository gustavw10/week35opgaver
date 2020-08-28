/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CustomerEko;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gustav
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public CustomerEko addCustomer(String name, String address){
        CustomerEko customer = new CustomerEko(name, address);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public Long getCustomerCount(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CustomerEko c", Long.class);
        long customerCount = query.getSingleResult();
        return customerCount;
    }
    
    
    public CustomerEko findCustomer(Long id){
         EntityManager em = emf.createEntityManager();
        try{
            CustomerEko cus = em.find(CustomerEko.class,id);
            return cus;
        }finally {
            em.close();
        }
    }
    public List<CustomerEko> getAllCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<CustomerEko> query = 
                       em.createQuery("Select customer from CustomerEko customer",CustomerEko.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    CustomerEko b1 = facade.addCustomer("ekoTEST", "ekoROAD");
    CustomerEko b2 = facade.addCustomer("ekoTESTTWO", "ekoroad");
   
    System.out.println("1: "+facade.findCustomer(b1.getId()).getName());
    System.out.println("2: "+facade.findCustomer(b2.getId()).getName());
  
    
     EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CustomerEko c", Long.class);
        long customerCount = query.getSingleResult();
        System.out.println(customerCount);

    }

    
}
