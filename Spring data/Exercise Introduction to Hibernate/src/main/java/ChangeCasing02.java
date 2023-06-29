import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;

public class ChangeCasing02 {

    public static void main(String[] args) {

        EntityManager entityManager = Units.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> fromTown = entityManager.createQuery("FROM Town", Town.class).getResultList();

        for (Town town : fromTown) {
            if (town.getName().length() > 5) {
                entityManager.detach(town);
                continue;
            }
            town.setName(town.getName().toUpperCase());
            entityManager.persist(town);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
