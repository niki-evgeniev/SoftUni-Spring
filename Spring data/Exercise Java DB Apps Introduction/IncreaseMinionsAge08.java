import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static messeges.ConstantMessages.ENTER_IDS_MINIONS;

public class IncreaseMinionsAge08 {
    public static void main(String[] args) throws SQLException {

        Connection sqlConnection = Units.getSqlConnection();

        PreparedStatement updateAgeMinions = getUpdateAgeMinions(sqlConnection);

        PreparedStatement getAllMinions = getMinions(sqlConnection);

        String[] readFromConsole = readAllNumberFromConsole();

        updateAllId(updateAgeMinions, readFromConsole);

        ResultSet resultSet = getAllMinions.executeQuery();

        printMinions(resultSet);
    }




    private static String[] readAllNumberFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ENTER_IDS_MINIONS);
        return scanner.nextLine().split("\\s+");
    }

    private static void printMinions(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String nameMinion = resultSet.getString("name");
            int ageMinion = resultSet.getInt("age");
            System.out.println(nameMinion + " " + ageMinion);
        }
    }

    private static void updateAllId(PreparedStatement updateAgeMinions, String[] readFromConsole) throws SQLException {
        for (String s : readFromConsole) {
            int id = Integer.parseInt(s);
            updateAgeMinions.setInt(1, id);

            updateAgeMinions.executeUpdate();
        }
    }

    private static PreparedStatement getMinions(Connection sqlConnection) throws SQLException {
        PreparedStatement getAllMinions = sqlConnection.prepareStatement(
                "select name ,\n" +
                        "       age\n" +
                        "from minions;\n");
        return getAllMinions;
    }

    private static PreparedStatement getUpdateAgeMinions(Connection sqlConnection) throws SQLException {
        PreparedStatement updateAgeMinions = sqlConnection.prepareStatement(
                "update minions\n" +
                        "set age = age + 0,\n" +
                        "    name = lower(name)\n" +
                        "where id = ?;");
        return updateAgeMinions;
    }
}
