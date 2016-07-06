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
			return "Error: " +e.getMessage();
		}
	}
	
	public static double getDoubleInput(String prompt) {
		String input = InputHelper.getInput(prompt);
		return Double.parseDouble(input);
	}
		public static java.sql.Date getSQLDate(String invitation) throws Exception {
		System.out.println(invitation);
		StringBuilder sb=new StringBuilder(10);
		StringBuilder sbT=new StringBuilder(10);
		char r=0;
		//Year
		System.out.print("Enter year: ");
		do{
			r=(char)System.in.read();
			if (r!=0)sbT.append(r);
		}
		while (r!='\n');
		sb.append((Integer.parseInt(sbT.toString().trim())));
		sb.append('-');
		
		sbT.delete(0, sbT.length()-1);
		r=0;
		//Month	
		System.out.print("Enter month: ");
		do{
			r=(char)System.in.read();
			if (r!=0)sbT.append(r);
		}
		while (r!='\n');
		sb.append((Integer.parseInt(sbT.toString().trim())));
		sb.append('-');
		
		sbT.delete(0, sbT.length()-1);
		r=0;
		//Day
		System.out.print("Enter day: ");
		do{
			r=(char)System.in.read();
			if (r!=0)sbT.append(r);
		}
		while (r!='\n');
		sb.append((Integer.parseInt(sbT.toString().trim())));
		
		System.out.println(sb.toString());
		try {
			return java.sql.Date.valueOf(sb.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(new java.sql.Date(new Date().getTime()).toString());
			return new java.sql.Date(new Date().getTime());
		}
		
	}
}
