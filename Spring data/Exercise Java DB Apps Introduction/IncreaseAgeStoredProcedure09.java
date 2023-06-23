import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure09 {
    public static void main(String[] args) throws SQLException {
        Connection sqlConnection = Units.getSqlConnection();

        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        PreparedStatement preparedStatement = sqlConnection.prepareStatement(
                "SELECT name,age from minions\n" +
                        "where id = ? ;");

        preparedStatement.setInt(1, number);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.printf("%s %d %n", resultSet.getString("name"),
                    resultSet.getInt("age"));
        }


    }
}
