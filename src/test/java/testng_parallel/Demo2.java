package testng_parallel;

import org.testng.annotations.Test;

public class Demo2 {
	
	@Test
	public void method2() {
		System.out.println("M2 --> " + Thread.currentThread().getId());
	}
	
	@Test
	public void method22() {
		System.out.println("M22 --> " + Thread.currentThread().getId());
	}
	
	@Test
	public void method222() {
		System.out.println("M222 --> " + Thread.currentThread().getId());
	}

}
