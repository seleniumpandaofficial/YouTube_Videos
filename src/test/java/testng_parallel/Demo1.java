package testng_parallel;

import org.testng.annotations.Test;

public class Demo1 {
	
	@Test
	public void method1() {
		System.out.println("M1 --> " + Thread.currentThread().getId());
	}
	
	@Test
	public void method11() {
		System.out.println("M11--->" + Thread.currentThread().getId());
	}
	
	@Test
	public void method111() {
		System.out.println("M111 --->" + Thread.currentThread().getId());
	}

}
