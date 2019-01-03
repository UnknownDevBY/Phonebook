package sample;

import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SqlConnectionToDb {

    public final String URL = "jdbc:mysql://localhost:3306/schema1";
    public final String USER = "root";
    public final String PASSWORD = "root";
    public static final String TABLE_NAME = "Contacts";
    private Connection connection;
    private Statement statement;
    private static SqlConnectionToDb instance;

    private SqlConnectionToDb() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
        statement.execute("SET GLOBAL time_zone = '" + displayTimeZone(TimeZone.getTimeZone(Calendar.getInstance().getTimeZone().getID())) + "';");
        statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "num VARCHAR(20)," +
                "info VARCHAR(100));");
    }

    public static SqlConnectionToDb getInstance() throws SQLException {
        if(instance == null)
            instance = new SqlConnectionToDb();
        return instance;
    }

    public void queryExec(String query) throws SQLException {
        statement.execute(query);
    }

    public ResultSet selectFromDatabase(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public void insertIntoDatabase(String num, String name) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES ('" + num + "', '" + name + "');");
    }

    private String displayTimeZone(TimeZone tz) {
        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        minutes = Math.abs(minutes);
        if (hours >= 0)
            return String.format("+%d:%02d", hours, minutes);
        else return String.format("-%d:%02d", hours, minutes);
    }

}
