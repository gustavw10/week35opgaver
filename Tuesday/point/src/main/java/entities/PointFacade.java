/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Gustav
 */
public class PointFacade {
    
    private static EntityManagerFactory emf;
    private static PointFacade instance;

    private PointFacade(){
    }
    
    public static PointFacade getPointFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PointFacade();
        }
        return instance;
    }
    
    public Point findById (int id){
        EntityManager em = emf.createEntityManager();
        try{
            Point point = em.find(Point.class,id);
            return point;
        }
        catch (Exception e){
            throw new NullPointerException();
        }
                finally {
            em.close();
        }
    }
    
    
}
