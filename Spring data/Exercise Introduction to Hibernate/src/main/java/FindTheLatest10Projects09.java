import entities.Project;

import java.util.Comparator;

public class FindTheLatest10Projects09 {
    public static void main(String[] args) {


        Units.createEntityManager()
                .createQuery("FROM Project ORDER BY startDate DESC, name", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing((f) -> f.getName()))
                .forEach(p -> p.printInfo());
    }
}
