import java.sql.*;
import java.util.Properties;

public class GetVillainsNames02 {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "12345");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select v.name,\n" +
                "count(distinct mv.minion_id) as count\n" +
                "from villains as v\n" +
                "join minions_villains mv on v.id = mv.villain_id\n" +
                "group by v.id\n" +
                "having count > ?\n" +
                "order by count desc;");

        preparedStatement.setInt(1, 15);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int count = resultSet.getInt("count");

            System.out.printf("%s %d", name, count);
        }


        connection.close();
    }
}
