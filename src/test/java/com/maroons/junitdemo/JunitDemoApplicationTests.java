package com.maroons.junitdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.maroons.junitdemo.embedded.EmbeddedMysqlManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitDemoApplicationTests {
	protected EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void loadDatabase() {
		mysqlManager.reloadSchema();
	}

}
