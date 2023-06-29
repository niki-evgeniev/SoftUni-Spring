import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager entityManager = Units.createEntityManager();

        String fullName = getFullName(scanner);

        List<Employee> checkContainName = ifContainEmployeeName(entityManager, fullName);

        print(entityManager, checkContainName);

    }


    private static void print(EntityManager entityManager, List<Employee> checkContainName) {
        if (checkContainName.isEmpty()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
        entityManager.close();
    }

    private static List<Employee> ifContainEmployeeName(EntityManager entityManager, String fullName) {
        List<Employee> checkContainName = entityManager.createQuery(
                        "FROM Employee WHERE concat(firstName, ' ' , lastName) = :fullName ", Employee.class)
                .setParameter("fullName", fullName).getResultList();
        return checkContainName;
    }

    private static String getFullName(Scanner scanner) {
        System.out.println("Enter full name");
        return scanner.nextLine();
    }
}


