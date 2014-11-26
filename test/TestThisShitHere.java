
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.JavaCodedFacadeShitIsDone;

public class TestThisShitHere {
    
    JavaCodedFacadeShitIsDone facade = JavaCodedFacadeShitIsDone.getFacade(true);
    Gson gson = new Gson();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SemesterProjectFristShitPU");
    static EntityManager em = emf.createEntityManager();
    List<Users> list;
    
    public TestThisShitHere() {
        list = new ArrayList();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   @Test
    public void testGetPersonAsJson(){
        Users p1 = facade.addPersonFromGson(gson.toJson(new Users("bbb", "bbb", "bbb","mail.gmail.com")));
         Users p2 = facade.addPersonFromGson(gson.toJson(new Users("VALA", "Mata", "23563","mail.gmail.com")));
         long nr =p1.getId();
         long nr2 =p2.getId();
         list.add(new Users(nr, "bbb", "bbb", "bbb","mail.gmail.com"));
         list.add(new Users(nr2,"VALA", "Mata", "23563","mail.gmail.com"));
String result = facade.getPersonAsJson(p1.getId());

        System.out.println(result);
        facade.delete(p1.getId());
        facade.delete(p2.getId());
assertEquals(gson.toJson(p1),result);
    }
    
    @Test
    public void addPersonTest(){
    Users person = facade.addPersonFromGson(gson.toJson(new Users("koala", "123456", "blavbla@grgre.com", "user")));
    String expectedJsonString = gson.toJson(person);
    String actual = facade.getPersonAsJson(person.getId());
    facade.delete(person.getId());
    assertEquals(expectedJsonString, actual);
    }
    
    @Test
    public void testTheDelete(){
    Users person = facade.addPersonFromGson(gson.toJson(new Users("koala", "123456", "blavbla@grgre.com", "user")));
    facade.delete(person.getId());
    String p =facade.getPersonAsJson(person.getId());
    boolean  cute = true;
    if(p.equals(null)){
        cute = false;
    }
       assertTrue(cute);
  
    }
    
    
}
