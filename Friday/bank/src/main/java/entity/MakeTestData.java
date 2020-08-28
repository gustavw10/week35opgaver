/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustav
 */
public class MakeTestData {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        // public BankCustomer(String firstName, String accountNumber, 
        //double balance, int customerRanking, String internalInfo) {
     
        EntityManager em = emf.createEntityManager();
        
        BankCustomer customer = new BankCustomer("Eko", "First", "00101", 7500, 183, "Poor customer");
        BankCustomer customer2 = new BankCustomer("Ab", "Second", "00106", 52300, 2183, "Good customer");
        BankCustomer customer3 = new BankCustomer("Proctor", "Third", "00050", 299000, 3, "Good customer");
        BankCustomer customer4 = new BankCustomer("Foster", "Fourth", "00251", 400, 235, "Poor customer");
        BankCustomer customer5 = new BankCustomer("Noah", "Fifth", "00351", 8900, 511, "Poor customer");
        BankCustomer customer6 = new BankCustomer("Buster", "Sixth", "00333", 67900, 58, "Good customer");
        
        em.getTransaction().begin();
        em.persist(customer);
        em.persist(customer2);
        em.persist(customer3);
        em.persist(customer4);
        em.persist(customer5);
        em.persist(customer6);
        em.getTransaction().commit();   
    }
    
}
