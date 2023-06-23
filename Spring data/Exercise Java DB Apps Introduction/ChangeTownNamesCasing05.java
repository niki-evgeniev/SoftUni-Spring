import messeges.ConstantMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ChangeTownNamesCasing05 {
    public static void main(String[] args) throws SQLException {
        Connection sqlConnection = Units.getSqlConnection();

        PreparedStatement psGetCountTowns = getGetCountTowns(sqlConnection);

        PreparedStatement psGetAllTownsNames = getGetAllTowns(sqlConnection);


        System.out.println(ConstantMessages.ENTER_TOWN);
        Scanner scanner = new Scanner(System.in);

        String nameTownScanner = scanner.nextLine();

        psGetAllTownsNames.setString(1, nameTownScanner);
        psGetCountTowns.setString(1, nameTownScanner);

        ResultSet resultSetAllTownsNames = psGetAllTownsNames.executeQuery();
        ResultSet resultSetCountTowns = psGetCountTowns.executeQuery();

        if(!resultSetCountTowns.next()){
            System.out.println(ConstantMessages.NO_TOWN_WITH_THIS_NAME);
            sqlConnection.close();
            return;
        }

        int count = resultSetCountTowns.getInt("townCount");

        System.out.printf(ConstantMessages.NUMBER_OF_ALL_TOWNS, count);

        List<String> townNames = new ArrayList<>();

        getNameTowns(resultSetAllTownsNames, townNames);

        System.out.println(String.join(", ", townNames));

    }





    private static void getNameTowns(ResultSet resultSetAllTowns, List<String> townNames) throws SQLException {
        while (resultSetAllTowns.next()){
            String nameTown = resultSetAllTowns.getString("townName");
            townNames.add(nameTown);
        }
    }

    private static PreparedStatement getGetAllTowns(Connection sqlConnection) throws SQLException {
        PreparedStatement psGetAllTowns = sqlConnection.prepareStatement(
                "select UPPER(name) as townName " +
                        "from towns\n" +
                        "where country like ?;");
        return psGetAllTowns;
    }

    private static PreparedStatement getGetCountTowns(Connection sqlConnection) throws SQLException {
        PreparedStatement psGetCountTowns = sqlConnection.prepareStatement(
                "select count(country) as townCount\n" +
                        "from towns\n" +
                        "where country like ?\n" +
                        "group by country;");
        return psGetCountTowns;
    }
}
