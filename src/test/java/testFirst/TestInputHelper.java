package testFirst;

import static org.junit.Assert.*;
import java.util.Calendar;
import main.Start;
import org.junit.Test;
import beans.WaterParam;

public class TestInputHelper {

/*	@Test
	public void testGetInput() {
		String s = InputHelper.getInput("Hi");
		assertEquals("test", s);
	}*/
	@Test
	public void testgetWaterParamByID() {
		long i = 8;
		Calendar cd =Calendar.getInstance();
		cd.set(2015, 0, 2);
		System.out.println(cd.getTime());
		WaterParam wp=new WaterParam(i,
				new java.sql.Date(cd.getTime().getTime()),
				"COBAS", 
				16.2d);
		System.out.println("1. " +Start.getWaterParamByID(i).toString());
		System.out.println("2. " +wp);
		assertEquals(Start.getWaterParamByID(i).equals(wp), true);
	}
}
