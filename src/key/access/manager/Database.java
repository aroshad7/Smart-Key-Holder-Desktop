package key.access.manager;

//STEP 1. Import required packages

import java.sql.*;
import java.util.ArrayList;

public class Database {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/key access manager";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "password";

    ArrayList session = new ArrayList();
    Connection conn = null;
    Statement stmt = null;

    public void connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    //STEP 4: Execute a query

    public boolean insertAdmin(String name, String position, String username, String password, String role) {
        
        return true;
    }

    public ArrayList adminAuthenticate(String username, String password) throws SQLException {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM administrators WHERE username = '" + username + "' and password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            session.add(rs.getString("name"));
            session.add(rs.getString("position"));
            session.add(rs.getString("username"));
            session.add(rs.getString("password"));
            session.add(rs.getString("role"));
            session.add(rs.getString("status"));

            rs.close();
            stmt.close();
            conn.close();
            return session;

        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    return null;
                }
            } catch (SQLException se2) {
                return null;
            }
            try {
                if (conn != null) {
                    conn.close();
                    return null;
                }
            } catch (SQLException se) {
                se.printStackTrace();
                return null;
            }
        }
    }
}
