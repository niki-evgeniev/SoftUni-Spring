import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName11 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name starts with a pattern");
        String patternFromConsole = scanner.nextLine();

        EntityManager entityManager = Units.createEntityManager();


        List<Employee> resultList = entityManager.createQuery(
                        "FROM Employee WHERE firstName LIKE CONCAT(:pattern, '%')", Employee.class)
                .setParameter("pattern", patternFromConsole)
                .getResultList();

        resultList.forEach(employee -> System.out.printf("%s %s - %s - (%s)%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getSalary()));
    }
}
