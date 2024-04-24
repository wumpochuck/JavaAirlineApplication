package sample;

import java.sql.SQLException;

public class DataBaseLauncher {

    public static void main(String[] args) throws SQLException {

        DataBaseHandler dbc = new DataBaseHandler();

        dbc.createUsersTable();
        dbc.createFlightTable();
        dbc.createTicketsTable();
    }

}
