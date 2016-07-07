package testFirst;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import main.Start;

import org.junit.Test;

import beans.WaterParam;

public class TestWaterParam {

	@Test
	public void testgetWaterParamByID() {
		long i = 8;
		Calendar cd =Calendar.getInstance();
		cd.set(2015, 0, 2);
		WaterParam wp=new WaterParam(i,
				new java.sql.Date(cd.getTime().getTime()),
				"COBAS", 
				16.2d);
		assertEquals(Start.getWaterParamByID(i).equals(wp), true);
	}
	
	@Test
	public void testEqualsObject() {
		Calendar cd =Calendar.getInstance();
		cd.set(2015, 0, 2);
		WaterParam wp1Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16.2"));
		cd.set(2015, 0, 2);
		WaterParam wp2Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16.2"));
		assertEquals(wp1Param, wp2Param);
	}
	
	@Test
	public void testInqualsObject1() {
		Calendar cd =Calendar.getInstance();
		cd.set(2015, 0, 1);
		WaterParam wp1Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16.2"));
		cd.set(2015, 0, 2);
		WaterParam wp2Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16.2"));
		assertFalse(wp1Param.equals(wp2Param));
	}

	@Test
	public void testInqualsObject2() {
		Calendar cd =Calendar.getInstance();
		cd.set(2015, 0, 1);
		WaterParam wp1Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16.2"));
		cd.set(2015, 0, 1);
		WaterParam wp2Param = new WaterParam(1, new Date(cd.getTime().getTime()), 
				"COBAS", Double.valueOf("16"));
		assertFalse(wp1Param.equals(wp2Param));
	}
}
