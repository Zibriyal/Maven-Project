package mypkg;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyCalcTest {
	
	MyCalc calc = new MyCalc();

	@Test
	public void test() {
		
		assertEquals (70,calc.sum(40,30));
		assertEquals (10,calc.diff(40,30));
		
	}

}
