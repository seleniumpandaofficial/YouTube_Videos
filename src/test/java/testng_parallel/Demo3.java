package testng_parallel;

import org.testng.annotations.Test;

public class Demo3 {
	
	@Test
	public void method3() {
		System.out.println("M3 --> " + Thread.currentThread().getId());
	}
	
	@Test
	public void method33() {
		System.out.println("M33 -->" + Thread.currentThread().getId());
	}
	
	@Test
	public void method333() {
		System.out.println("M333 --> " + Thread.currentThread().getId());
	}

}
