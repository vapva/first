package main;


import java.sql.*;
import java.text.*;
import java.util.Locale;

import DbUtils.DbSingleton;
import auxiliary.InputHelper;

public class Start {
	private static final String QR1 = "SELECT * FROM tblWaterParameters";
	private static final String QR2 = "SELECT * FROM tblWaterParameters WHERE pDate<=?";	

	public Start() {
		super();
		System.out.println();
	}

	public static void runDB() {
		try(
				Statement stmt = DbSingleton.getInstanse().getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs  = stmt.executeQuery(QR1);
				){
			System.out.println("Connection is successful");
			rs.last();
			System.out.println("Number of Rows: "+ rs.getRow());
			rs.beforeFirst();
			if (!(rs.getWarnings()==null)) System.out.println(rs.getWarnings().toString());
			StringBuilder sb = new StringBuilder(100);
			System.out.println(String.format("%-15s", "pDate")  +
					String.format("%-25s",  "ControlSerialNumber") +
					String.format("%-15s",   "ParameterValue"));
			while (rs.next()){
				sb.append((String.format("%-15s", DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.ENGLISH).format(rs.getDate("pDate")))

						+String.format("%-25s",rs.getObject("ControlSerialNumber", String.class)
						+String.format("%-15s",NumberFormat.getNumberInstance(new Locale("uk")).format(rs.getObject("ParameterValue",Double.class)))))+"\n");
			}
			System.out.println(sb.toString());
			System.out.println("\n" +"Number of symbols in cache: "+sb.length());
		}catch (SQLException e) {
			System.err.println("Err.Code: " + e.getErrorCode() + "Message: " + 
					e.getMessage() + "SQL State: " +e.getSQLState());
			;
		}


	}	
	public static void main(String[] args) {
		System.out.println("Oups!");
//		runDB();
		System.out.println("New Session");
		runDBwithParameters();
	}
	public static void runDBwithParameters(){
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try{
			stmt = DbSingleton.getInstanse().getConnection().prepareStatement(QR2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("Connection is successful");
			try {
				stmt.setDate(1, InputHelper.getSQLDate("Enter Date Parameter "));
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs=stmt.executeQuery();
			rs.last();
			System.out.println("Number of Rows: "+ rs.getRow());
			rs.beforeFirst();
			if (!(rs.getWarnings()==null)) System.out.println(rs.getWarnings().toString());
			StringBuilder sb = new StringBuilder(100);
			System.out.println(String.format("%-15s", "pDate")  +
					String.format("%-25s",  "ControlSerialNumber") +
					String.format("%-15s",   "ParameterValue"));
			while (rs.next()){
				sb.append((String.format("%-15s", DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.ENGLISH).format(rs.getDate("pDate")))

						+String.format("%-25s",rs.getString("ControlSerialNumber"))
						+String.format("%-15s",NumberFormat.getNumberInstance(new Locale("uk")).format( rs.getDouble("ParameterValue"))))+"\n");
			}
			System.out.println(sb.toString());
			System.out.println("\n" +"Number of symbols in cache: "+sb.length());
		}catch (SQLException e) {
			System.err.println("Err.Code: " + e.getErrorCode() + "\nMessage: " + 
					e.getMessage() + "\nSQL State: " +e.getSQLState());
			;
		}
		finally{
			if (stmt!=null) try {
				stmt.close();
			} catch (SQLException e) {
			}
			if (rs!=null) try {
				rs.close();
			} catch (SQLException e) {
			}
			
		}
	}
}
