package main;


import java.math.BigDecimal;
import java.sql.*;
import java.text.*;
import java.util.Locale;

import beans.WaterParam;
import auxiliary.InputHelper;
import DbUtils.DbSingleton;

public class Start {
	private static final String QR1 = "SELECT * FROM tblWaterParameters LIMIT 0,100";
	private static final String QR2 = "SELECT * FROM tblWaterParameters WHERE pDate<=?";	

	public static void runDB() {
		try(
				Statement stmt = DbSingleton.getInstanse().getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs  = stmt.executeQuery(QR1);
				){
			rs.last();
			System.out.println("Number of the last row: "+ rs.getRow());
			rs.beforeFirst();
			if (!(rs.getWarnings()==null)) System.out.println(rs.getWarnings().toString());
			StringBuilder sb = new StringBuilder(100);
			if (rs.first())System.out.println(String.format("%-15s", "pDate")  +
					String.format("%-25s",  "ControlSerialNumber") +
					String.format("%-15s",   "ParameterValue"));
			rs.beforeFirst();
			while (rs.next()){
				sb.append((String.format("%-15s", DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.ENGLISH).format(rs.getObject("pDate", Date.class)))
						+String.format("%-25s",rs.getObject("ControlSerialNumber", String.class))
						+String.format("%-15s",NumberFormat.getNumberInstance(new Locale("uk")).format(rs.getObject("ParameterValue",BigDecimal.class))))
						+"\n");
			}
			if (sb.length()>0)System.out.println(sb.toString());
			System.out.println("Quantity of symbols in cache: "+sb.length());
		}catch (SQLException e) {
			System.err.println("Err.Code: " + e.getErrorCode() + "Message: " + 
					e.getMessage() + "SQL State: " +e.getSQLState());
		}
	}	
 
	public static void main(String[] args) {
		System.out.println("Starting ..." + "\nQuerying first 100 records ...");
		runDB();
		System.out.println("New Session");
		runDBwithParameters();
		if (!(DbUtils.DbSingleton.getInstanse().getConnection()==null)) try {
			System.out.println("Closing DB ...");
			DbUtils.DbSingleton.getInstanse().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void runDBwithParameters(){
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try{
			stmt = DbSingleton.getInstanse().getConnection().prepareStatement(QR2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("Connection is successful");
			try {
				stmt.setDate(1, InputHelper.getSQLDate("Enter Date Parameter ..."));
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Errorneous input, current date is taken ...");
				stmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			}
			rs=stmt.executeQuery();
			rs.last();
			System.out.println("Number of the last row: "+ rs.getRow());
			if (!(rs.getWarnings()==null)) System.out.println(rs.getWarnings().toString());
			StringBuilder sb = new StringBuilder(100);
			if (rs.first())System.out.println(String.format("%-15s", "pDate")  +
					String.format("%-25s",  "ControlSerialNumber") +
					String.format("%-15s",   "ParameterValue"));
			
			rs.beforeFirst();
			while (rs.next()){
				sb.append((String.format("%-15s", DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.ENGLISH).format(rs.getObject("pDate", Date.class)))
						+String.format("%-25s",rs.getObject("ControlSerialNumber", String.class))
						+String.format("%-15s",NumberFormat.getNumberInstance(new Locale("uk")).format( rs.getObject("ParameterValue",BigDecimal.class))))+"\n");
			}
			if (sb.length()>0) System.out.println(sb.toString());
			System.out.println("Quantity of symbols in cache: "+sb.length());
		}catch (SQLException e) {
			System.err.println("Err.Code: " + e.getErrorCode() + "\nMessage: " + 
					e.getMessage() + "\nSQL State: " +e.getSQLState());
		}
		finally{
			if (stmt!=null) try {
				stmt.close();
			} catch (SQLException e) {}
			if (rs!=null) try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
	
	public static WaterParam getWaterParamByID(long i) {
		final String QR="SELECT * FROM  tblWaterParameters WHERE ID=?";
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=DbUtils.DbSingleton.getInstanse().getConnection().prepareStatement(QR,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setLong(1, i);
			rs=stmt.executeQuery();
			rs.last();
			rs.first();
			if (rs.getRow()==1){
				WaterParam wp=new WaterParam(i,
						rs.getDate("pDate"/*, Date.class*/),
						rs.getObject("ControlSerialNumber", String.class),
						rs.getObject("ParameterValue", Double.class));
				return wp;
			}
		} catch (Exception e) {
			System.out.println("Nothing was retrieved");
			e.printStackTrace();
			return null;
		}finally{
			if (stmt!=null) try {
				stmt.close();
			} catch (SQLException e) {}
			if (rs!=null) try {
				rs.close();
			} catch (SQLException e) {}
		}
		return null;
	}
	
	public static boolean insertWaterParam(WaterParam obj) {
		final String QR="INSERT INTO tblWaterParameters (pDate, ControlSerialNumber, ParameterValue)"
				+ " VALUES (?,?,?)";
		ResultSet rs=null;
		PreparedStatement stmt=null;
		if (obj==null || !(obj instanceof WaterParam)) return false;
		try {
			stmt=DbUtils.DbSingleton.getInstanse().getConnection().prepareStatement(QR, Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, obj.getpDate());
			stmt.setString(2, obj.getControlSerialNumber());
			stmt.setDouble(3, obj.getParameterValue());
			if (stmt.executeUpdate()==0) return false;
			rs=stmt.getGeneratedKeys();
			rs.next();
			obj.setID(rs.getInt(1));
			return true;
		} catch (Exception e) {
			System.out.println("Operation failed");
			e.printStackTrace();
			return false;
		}finally{
			if (stmt!=null) try {
				stmt.close();
			} catch (SQLException e) {}
			if (rs!=null) try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
}
