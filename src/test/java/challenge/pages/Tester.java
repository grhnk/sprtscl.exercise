package challenge.pages;

import org.junit.Test;

import challenge.utilities.Config;

public class Tester {
	
	@Test
	public void test(){
		System.out.println(Config.getProperty("browser"));
	}

}
