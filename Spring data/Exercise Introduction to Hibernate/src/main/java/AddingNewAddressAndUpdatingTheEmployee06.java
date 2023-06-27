import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AddingNewAddressAndUpdatingTheEmployee06 {
    public static void main(String[] args) {
        EntityManager entityManager = Units.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String addressToAdd = "Vitoshka 15";

        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText(addressToAdd);
        entityManager.persist(address);

        System.out.println("Enter Employee last name");
        String nameToAddAddress = scanner.nextLine();

        List<Employee> employeesList = entityManager.createQuery(
                        "FROM Employee WHERE lastName = :lastName", Employee.class)
                .setParameter("lastName", nameToAddAddress)
                .getResultList();

        Set<Employee> db = new HashSet<>(employeesList);
        employeesList.forEach(e -> e.setAddress(address));

        System.out.println();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
