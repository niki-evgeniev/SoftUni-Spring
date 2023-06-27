import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class AddressesWithEmployeeCount07 {
    public static void main(String[] args) {

        EntityManager entityManager = Units.createEntityManager();

        List<Address> employeeCount = entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address adr : employeeCount) {
            System.out.printf(adr.printGeneralInformation());
        }
    }
}

