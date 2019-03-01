package com.rcg.hellojunit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

//@RunWith(JUnitPlatform.class)
public class GreetingImplTest {

	private Greeting greeting;

	@BeforeEach
	public void setup() {
		System.out.println("Setup");
		greeting = new GreetingImpl();
	}
	
	@Test
	public void greetShouldReturnAValidOutput() {
		
		
		String result = greeting.greet("Junit");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello Junit", result);
	}
	
	@Test
	public void greetShouldThrowAnException_For_NameIsNull() {
		
		
		Assertions.assertThrows(IllegalArgumentException.class, ()->{greeting.greet(null);});
		
		
	}
	
	@Test
	public void greetShouldThrowAnException_For_NameIsBlank() {
		
		Assertions.assertThrows(IllegalArgumentException.class, ()->{greeting.greet("");});
		
		
	}

	
	@AfterEach
	public void teardown() {
		System.out.println("teardown");
		greeting = null;
	}
	
}
