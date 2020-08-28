/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.CustomerDTO;
import entity.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gustav
 */
public class CustomerFacade {
    
    
    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    public static CustomerFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public CustomerDTO getCustomerByID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            BankCustomer customer = em.find(BankCustomer.class,id);
            CustomerDTO customerDTO = new CustomerDTO(customer);
            return customerDTO;
        }finally {
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("Select e from Customer e WHERE e.name = :name", BankCustomer.class);
            query.setParameter("name", name);
            List<BankCustomer> eList = query.getResultList();
            List<CustomerDTO> eListDTO = null;
            for (int i = 0; i > eList.size(); i++){
                CustomerDTO e = new CustomerDTO(eList.get(i));
                eListDTO.add(e);
            }
            return eListDTO;
        }finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer cust){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }
    
    public List<CustomerDTO> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("Select e from Employee e", BankCustomer.class);
            List<BankCustomer> eList = query.getResultList();
            List<CustomerDTO> eListDTO = null;
            for (int i = 0; i > eList.size(); i++){
                CustomerDTO e = new CustomerDTO(eList.get(i));
                eListDTO.add(e);
            }
            return eListDTO;
        }finally {
            em.close();
        }
    }


}
