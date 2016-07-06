package DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {
	private static final String CONNECTION_STRING="jdbc:ucanaccess://docs/App v27.accdb";
	private static  Connection conn=null;
	private static DbSingleton me=null;
	
	private DbSingleton(){
		try {
			conn=DriverManager.getConnection(CONNECTION_STRING);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static DbSingleton getInstanse(){
		if (me==null){
			me = new DbSingleton();
		}
		return me;
	}
	public Connection getConnection(){
		if (conn==null){
			try {
				conn = DriverManager.getConnection(CONNECTION_STRING);
				System.out.println("DB Connection is successful");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
