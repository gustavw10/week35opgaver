/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CustomerEko;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustav
 */
public class CustomerCreator {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CustomerEko customer = new CustomerEko("Hansen", "EKOROAD 1");
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        CustomerEko customer2 = new CustomerEko("Eko", "EKOROAD 2");
        em.getTransaction().begin();
        em.persist(customer2);
        em.getTransaction().commit();
        
    }
    
}
