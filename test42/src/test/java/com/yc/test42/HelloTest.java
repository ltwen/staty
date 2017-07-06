package com.yc.test42;

import org.junit.Assert;
import org.junit.Test;

public class HelloTest {

	@Test
	public void testSayHello() {
		Hello1 hello=new Hello1();
		String result=hello.sayHello("yc");
		Assert.assertSame("结果不一致！！！", "yc say Hello", result);
	}

}
