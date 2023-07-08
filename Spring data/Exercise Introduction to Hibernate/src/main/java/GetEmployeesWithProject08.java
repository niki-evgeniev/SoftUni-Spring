import entities.Employee;

import java.util.Scanner;

public class GetEmployeesWithProject08 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = Integer.parseInt(scanner.nextLine());

        Units.createEntityManager()
                .createQuery("FROM Employee WHERE id = :emplId", Employee.class)
                .setParameter("emplId", id)
                .getSingleResult()
                .print();
    }
}
