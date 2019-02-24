package com.rcg.hellojunit5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GreetingImplTest {

	private Greeting greeting;

	@Before
	public void setup() {
		System.out.println("Setup");
		greeting = new GreetingImpl();
	}
	
	@Test
	public void greetShouldReturnAValidOutput() {
		
		
		String result = greeting.greet("Junit");
		assertNotNull(result);
		assertEquals("Hello Junit", result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowAnException_For_NameIsNull() {
		
		greeting.greet(null);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowAnException_For_NameIsBlank() {
		
		greeting.greet("");
		
		
	}

	
	@After
	public void teardown() {
		System.out.println("teardown");
		greeting = null;
	}
	
}
