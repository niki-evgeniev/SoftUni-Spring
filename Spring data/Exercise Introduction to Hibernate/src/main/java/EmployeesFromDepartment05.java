import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesFromDepartment05 {
    public static void main(String[] args) {

        EntityManager entityManager = Units.createEntityManager();

        List<Employee> empFromDepartment = entityManager.createQuery(
                "FROM Employee WHERE department.id = :departmentId ORDER BY salary ASC, id ASC ", Employee.class)
                .setParameter("departmentId", 6)
                .getResultList();

        for (Employee emp: empFromDepartment) {
            System.out.printf("%s %s from Research and Development - %s%n", emp.getFirstName(), emp.getLastName(), emp.getSalary());
        }
    }
}
