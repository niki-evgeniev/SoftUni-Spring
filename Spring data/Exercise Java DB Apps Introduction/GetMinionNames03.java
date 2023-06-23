import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static messeges.ConstantMessages.*;

public class GetMinionNames03 {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "12345");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatementGetName = connection.prepareStatement(
                "select v.name from minions_villains\n" +
                        "                  join villains v on minions_villains.villain_id = v.id\n" +
                        "where villain_id = ?\n" +
                        "group by name;");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select name,age from minions as m\n" +
                        "join minions_villains mv on m.id = mv.minion_id\n" +
                        "where mv.villain_id = ?;");
        System.out.println(ENTER_VILLAIN_ID);
        Scanner scanner = new Scanner(System.in);

        int numberVillain = scanner.nextInt();
        int count = 0;

        preparedStatement.setInt(1, numberVillain);

        ResultSet resultSet = preparedStatement.executeQuery();


        preparedStatementGetName.setInt(1, numberVillain);

        ResultSet resultSetGetName = preparedStatementGetName.executeQuery();

        if (!resultSetGetName.next()) {
            System.out.println(NO_VILLAIN_EXIST);
            connection.close();
            return;
        }


        String getName = resultSetGetName.getString("name");
        System.out.printf(VILLAIN, getName);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            count++;

            System.out.printf(PRINT, count, name, age);

        }
        connection.close();

    }
}
