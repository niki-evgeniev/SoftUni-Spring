import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames07 {

    public static void main(String[] args) throws SQLException {

        Connection sqlConnection = Units.getSqlConnection();
        PreparedStatement preparedStatement = getPreparedStatement(sqlConnection);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> nameMinions = new ArrayList<>();
        fillList(resultSet, nameMinions);

        printMinions(nameMinions);

    }

    private static PreparedStatement getPreparedStatement(Connection sqlConnection) throws SQLException {
        return sqlConnection.prepareStatement(
                "select * from minions;");
    }

    private static void fillList(ResultSet resultSet, List<String> nameMinions) throws SQLException {
        for (int i = 0; resultSet.next(); i++) {
            String name = resultSet.getString("name");
            nameMinions.add(name);
        }
    }

    private static void printMinions(List<String> nameMinions) {
        int iteration = nameMinions.size() / 2;
        for (int i = 0; i < iteration; i++) {
            System.out.println(nameMinions.get(0));
            nameMinions.remove(nameMinions.get(0));
            System.out.println(nameMinions.get(nameMinions.size() - 1));
            nameMinions.remove(nameMinions.size() - 1);
        }
    }
}
