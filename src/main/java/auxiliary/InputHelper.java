package auxiliary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class InputHelper {
	public static String getInput( String prompt){
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(prompt);
		System.out.flush();
		try{
			return stdin.readLine();
		}catch (Exception e){
			System.out.println("Erroneous input");
			return "";
		}
	}
	
	public static double getDoubleInput(String prompt) {
		String input = InputHelper.getInput(prompt);
		return Double.parseDouble(input);
	}
	
	public static java.sql.Date getSQLDate(String invitation) throws Exception {
		String temp;
		StringBuilder sb=new StringBuilder(10);
		//Year
		temp="Enter year: ";
		temp=InputHelper.getInput(temp);
		sb.append((Integer.parseInt(temp.trim())));
		sb.append('-');
		
		//Month	
		temp="Enter month: ";
		temp=InputHelper.getInput(temp);
		sb.append((Integer.parseInt(temp.trim())));
		sb.append('-');
		
		//Day
		temp="Enter day: ";
		temp=InputHelper.getInput(temp);
		sb.append((Integer.parseInt(temp.trim())));
		
		System.out.println(sb.toString());
		try {
			return java.sql.Date.valueOf(sb.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(new java.sql.Date(new Date().getTime()).toString());
			return new java.sql.Date(new Date().getTime());
		}
		
	}
}
