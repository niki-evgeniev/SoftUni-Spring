import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Units {
    static EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("soft_uni")
                .createEntityManager();
    }
}
