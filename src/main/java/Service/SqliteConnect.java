package Service;

import java.sql.*;

public class SqliteConnect {
    public static final String URL = "jdbc:sqlite:identifier.sqlite";
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Connection getCoonnect(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return connection;
    }

    protected void close(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
