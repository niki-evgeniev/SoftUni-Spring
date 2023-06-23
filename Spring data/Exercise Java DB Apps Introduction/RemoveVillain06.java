import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static messeges.ConstantMessages.*;

public class RemoveVillain06 {
    public static void main(String[] args) throws SQLException {


        Connection sqlConnection = Units.getSqlConnection();

        PreparedStatement psGetNameWithId = getNameWithId(sqlConnection);
        PreparedStatement psServingMinions = getServingMinions(sqlConnection);
        PreparedStatement psDeleteFromMinionsVillains = DeleteMinionsVillains(sqlConnection);
        PreparedStatement psDeleteMinionWithId = DeleteMinionWithId(sqlConnection);

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        psGetNameWithId.setInt(1, id);
        ResultSet resultSetGetNameWithId = psGetNameWithId.executeQuery();
        if (checkForExistId(sqlConnection, resultSetGetNameWithId)) return;

        String nameMinionWithId = resultSetGetNameWithId.getString("name");

        psServingMinions.setInt(1, id);
        ResultSet resultSetMinionCount = psServingMinions.executeQuery();
        int countMinion = getCountServingMinion(resultSetMinionCount);

        sqlConnection.setAutoCommit(false);
        deleteFromDb(sqlConnection, psDeleteFromMinionsVillains, psDeleteMinionWithId, id);

        printF(nameMinionWithId, countMinion);
        sqlConnection.close();
    }

    private static void printF(String nameMinionWithId, int countMinion) {
        System.out.printf(DELETE_MINION_WITH_ID, nameMinionWithId);
        System.out.printf(COUNT_MINIONS_DELETE_FROM_MINIONS_VILLAINS, countMinion);
    }

    private static int getCountServingMinion(ResultSet resultSetMinionCount) throws SQLException {
        int countMinion = 0;
        while (resultSetMinionCount.next()) {
            countMinion = resultSetMinionCount.getInt("count_minions");
        }
        return countMinion;
    }

    private static boolean checkForExistId(Connection sqlConnection, ResultSet resultSetGetNameWithId) throws SQLException {
        if (!resultSetGetNameWithId.next()) {
            System.out.println(NO_VILLAIN_FOUND);
            sqlConnection.close();
            return true;
        }
        return false;
    }

    private static void deleteFromDb(Connection sqlConnection, PreparedStatement psDeleteFromMinionsVillains,
                                     PreparedStatement psDeleteMinionWithId, int id ) throws SQLException {
        try {
            psDeleteFromMinionsVillains.setInt(1, id);
            psDeleteFromMinionsVillains.executeUpdate();

            psDeleteMinionWithId.setInt(1, id);
            psDeleteMinionWithId.executeUpdate();

            sqlConnection.commit();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            sqlConnection.rollback();
        }
    }

    private static PreparedStatement DeleteMinionWithId(Connection sqlConnection) throws SQLException {
        PreparedStatement psDeleteMinionWithId = sqlConnection.prepareStatement(
                "delete\n"
                        + "from villains\n"
                        + "where id = ?;");
        return psDeleteMinionWithId;
    }

    private static PreparedStatement DeleteMinionsVillains(Connection sqlConnection) throws SQLException {
        PreparedStatement psDeleteFromMinionsVillains = sqlConnection.prepareStatement(
                "delete\n"
                        + "from minions_villains\n"
                        + "where villain_id = ?;");
        return psDeleteFromMinionsVillains;
    }

    private static PreparedStatement getServingMinions(Connection sqlConnection) throws SQLException {
        PreparedStatement psServingMinions = sqlConnection.prepareStatement(
                "select count(*) as count_minions\n"
                        + "from minions_villains\n"
                        + "where villain_id = ?;");
        return psServingMinions;
    }

    private static PreparedStatement getNameWithId(Connection sqlConnection) throws SQLException {
        PreparedStatement psGetNameWithId = sqlConnection.prepareStatement(
                "select name\n"
                        + "from villains\n"
                        + "where id = ?;");
        return psGetNameWithId;
    }
}
