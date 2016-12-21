package ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class MySqlConnection {
	private static final String URL = "jdbc:mysql://localhost/";
    private static final String DATABASE = "nexDealer";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "Cabrit0";

    protected Connection conn;
    protected Statement stm;

    protected void openDB(){
        try{
            Class.forName(DRIVER_CLASS).newInstance();
            conn = DriverManager.getConnection(URL+DATABASE, USER, PASSWORD);
            stm = (Statement) conn.createStatement();
        }catch (SQLException e){
            throw new RuntimeException("Database open Error:+\n\t"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void closeDB(){
        try{
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        }catch (SQLException e){
            throw new RuntimeException("Database close Error:+\n\t"+e.getMessage());
        }
    }
}
