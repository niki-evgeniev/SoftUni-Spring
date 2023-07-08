import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesWithASalaryOver50000_04 {
    public static void main(String[] args) {

        EntityManager entityManager = Units.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = getAllEmployeeWithSalary(entityManager);

        print(resultList);

        entityManager.close();
    }

    private static List<Employee> getAllEmployeeWithSalary(EntityManager entityManager) {
        List<Employee> resultList = entityManager.
                createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();
        return resultList;
    }

    private static void print(List<Employee> resultList) {
        for (Employee emp : resultList) {
            System.out.println(emp.getFirstName());
        }
    }
}
