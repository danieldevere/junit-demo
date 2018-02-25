package com.maroons.junitdemo;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.maroons.junitdemo.embedded.EmbeddedMysqlManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JunitDemoApplicationTests {
	protected EmbeddedMysqlManager mysqlManager = EmbeddedMysqlManager.getInstance();
	
	@Autowired
	TestRestTemplate template;

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testHello() {
		String response = template.getForEntity("/hello", String.class).getBody();
		assertThat(response).isEqualTo("Hello World!");
	}

}
