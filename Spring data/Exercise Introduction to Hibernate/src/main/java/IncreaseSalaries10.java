import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries10 {
    public static void main(String[] args) {

        List<String> listEmployees = List.of("Engineering", "Tool Design", "Marketing", "Information Services");

        EntityManager entityManager = Units.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery(
                        "FROM Employee WHERE department.name in :employeesList", Employee.class)
                .setParameter("employeesList", listEmployees)
                .getResultList();

        resultList.forEach(employee ->  employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();

//        resultList.forEach(employee -> System.out.println(employee.getFirstName() + employee.getLastName()
//                + "(" + employee.getSalary() + ")"));

        resultList.forEach(employee -> System.out.printf("%s %s (%.2f)%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary()));
    }
}
