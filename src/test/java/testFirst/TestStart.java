package testFirst;

import static org.junit.Assert.*;

import java.sql.Date;

import main.Start;

import org.junit.Test;

import beans.WaterParam;

public class TestStart {

	@Test
	public void testInsertWaterParam() {
		WaterParam wp = new WaterParam(1, new Date(new java.util.Date().getTime()),
				"COBAS", 13.3d);
		assertTrue(Start.insertWaterParam(wp));
		assertTrue(wp.getID()!=1);
		System.out.println("Obj's ID: " +wp.getID());
	}

}
