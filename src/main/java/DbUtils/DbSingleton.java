package DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {
	private static final String CONNECTION_STRING="jdbc:ucanaccess://docs/App v27.accdb";
	private static  Connection conn=null;
	private static DbSingleton me=null;
	
	//just for fun!
	static{
		System.out.println("Static initilizer should run first!");
	}
	
	private DbSingleton(){
		try {
			conn=DriverManager.getConnection(CONNECTION_STRING);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DbSingleton getInstance(){
		if (me==null){
			me = new DbSingleton();
			System.out.println("New Singleton's Instance is created");
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
	/**
	 * @param conn the conn to set
	 */
	public void setConnection(Connection conn) {
		DbSingleton.conn = conn;
	}
}
