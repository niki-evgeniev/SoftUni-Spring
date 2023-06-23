import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static messeges.ConstantMessages.*;

public class AddMinion04 {
    public static void main(String[] args) throws SQLException {

        Connection sqlConnection = Units.getSqlConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println(ENTER_MINION_INFO);
        String[] readNameAgeTown = scanner.nextLine().split("\\s+");
        String minionName = readNameAgeTown[1];
        int minionAge = Integer.parseInt(readNameAgeTown[2]);
        String minionTown = readNameAgeTown[3];

        String villainName = readValiantName(scanner);

        PreparedStatement checkForTownNameExist = getTownNameExist(sqlConnection);
        PreparedStatement insertTown = insertTown(sqlConnection);
        PreparedStatement checkVillainNameExist = getVillainNameExist(sqlConnection);
        PreparedStatement insertVillain = insertVillain(sqlConnection);
        PreparedStatement addMinion = addMinion(sqlConnection);
        PreparedStatement getIdTown = getGetIdTown(sqlConnection);

        checkForTownNameAndAdd(minionTown, checkForTownNameExist, insertTown);

        checkForValiantNameAndAdd(checkVillainNameExist, villainName, insertVillain);

        addMinion(minionName, minionAge, minionTown, addMinion, getIdTown);

        System.out.printf(SUCCESSFULLY_ADDED_MINION_TO_DB, minionName, villainName);

    }

    private static String readValiantName(Scanner scanner) {
        System.out.println(ENTER_VILLAIN);
        String[] villain = scanner.nextLine().split("\\s+");
        return villain[1];
    }

    private static void addMinion(String minionName, int minionAge, String minionTown, PreparedStatement addMinion, PreparedStatement getIdTown) throws SQLException {
        getIdTown.setString(1, minionTown);
        ResultSet getId = getIdTown.executeQuery();
        int idTown = 0;
        while (getId.next()) {
            idTown = getId.getInt("id");
        }

        addMinion.setString(1, minionName);
        addMinion.setInt(2, minionAge);
        addMinion.setInt(3, idTown);

        addMinion.executeUpdate();
    }

    private static void checkForValiantNameAndAdd(PreparedStatement checkVillainNameExist, String villainName, PreparedStatement insertVillain ) throws SQLException {
        checkVillainNameExist.setString(1, villainName);
        ResultSet checkVillain = checkVillainNameExist.executeQuery();
        if (!checkVillain.next()) {
            insertVillain.setString(1, villainName);
            insertVillain.executeUpdate();
            System.out.printf(VILLAIN_ADDED_TO_DB, villainName);
        }
    }

    private static void checkForTownNameAndAdd(String minionTown, PreparedStatement checkForTownNameExist, PreparedStatement insertTown) throws SQLException {
        checkForTownNameExist.setString(1, minionTown);
        ResultSet checkTown = checkForTownNameExist.executeQuery();
        if (!checkTown.next()) {
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            System.out.printf(TOWN_WAS_ADDED_TO_DB, minionTown);
        }
    }

    private static PreparedStatement getGetIdTown(Connection sqlConnection) throws SQLException {
        PreparedStatement getIdTown = sqlConnection.prepareStatement(
                "select id from towns\n" +
                        "where name = ?;");
        return getIdTown;
    }

    private static PreparedStatement addMinion(Connection sqlConnection) throws SQLException {
        PreparedStatement addMinion = sqlConnection.prepareStatement(
                "insert into minions(name, age, town_id)\n" +
                        "value(? ,?, ?);");
        return addMinion;
    }

    private static PreparedStatement insertVillain(Connection sqlConnection) throws SQLException {
        PreparedStatement insertVillain = sqlConnection.prepareStatement(
                "insert into villains(name, evilness_factor)\n" +
                        "    value (?,  'evil');");
        return insertVillain;
    }

    private static PreparedStatement getVillainNameExist(Connection sqlConnection) throws SQLException {
        PreparedStatement checkVillainNameExist = sqlConnection.prepareStatement(
                "select id, name from villains\n" +
                        "where name = ? ;");
        return checkVillainNameExist;
    }

    private static PreparedStatement insertTown(Connection sqlConnection) throws SQLException {
        PreparedStatement insertTown = sqlConnection.prepareStatement(
                "insert into towns(name)\n" +
                        "value (?);");
        return insertTown;
    }

    private static PreparedStatement getTownNameExist(Connection sqlConnection) throws SQLException {
        PreparedStatement checkForTownNameExist = sqlConnection.prepareStatement(
                "select id, name from towns\n" +
                        "where name = ?");
        return checkForTownNameExist;
    }
}
