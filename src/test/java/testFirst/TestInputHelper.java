package testFirst;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.InputHelper;

public class TestInputHelper {

	@Test
	public void testGetInput() {
		String s = InputHelper.getInput("Hi");
		assertEquals("test", s);
	}

}
