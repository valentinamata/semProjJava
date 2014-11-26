/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Users;

public class JavaCodedFacadeShitIsDone implements JavaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SemesterProjectFristShitPU");
    EntityManager em = emf.createEntityManager();
    private final Gson gson = new Gson();
    private static JavaCodedFacadeShitIsDone instance = new JavaCodedFacadeShitIsDone();
    
    public static JavaCodedFacadeShitIsDone getFacade(boolean reseet) {
        if (true) {
            instance = new JavaCodedFacadeShitIsDone();
        }
        return instance;
    }
    
    @Override
    public String getPersonAsJson(long id) {
     Users p = em.find(Users.class, id);
        return gson.toJson(p);
    }

    @Override
    public Users delete(long id) {
    Users p1 = em.find(Users.class, id);
        em.getTransaction().begin();
        em.remove(p1);
        em.getTransaction().commit();
        return p1;
    }

    @Override
    public Users addPersonFromGson(String json) {
    Users p = gson.fromJson(json, Users.class);
        em.getTransaction().begin();

        em.persist(p);

        em.getTransaction().commit();

        return p;
    }
    
}
