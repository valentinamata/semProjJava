/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testHere;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Users;

public class test {
     private static EntityManager createEntityManager() {
    EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("SemesterProjectFristShitPU");
    return emf.createEntityManager();
    }
     
     private static void fuckYouDB(){
         Users u1 = new Users("vala", "12345", "blabla@grgre.com", "admin");
         Users u2 = new Users("koala", "123456", "blavbla@grgre.com", "user");
        
         
         
         EntityManager em = createEntityManager();
    EntityTransaction transaction = em.getTransaction();
     transaction.begin();
     try {
    
      
      em.persist(u1);
      em.persist(u2);
      
      
      
     
      transaction.commit();
      
      }
    catch (Exception e) {
      transaction.rollback();
      }
     
     }
     

public static void main(String[] args) {
        new test().fuckYouDB();
    }
}




