package com.rest.restdemo;

import com.rest.restdemo.controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RestDemoApplicationTests {

	@Autowired
	private TransactionController transactionController;

	@Test
	void contextLoads() {
		// to ensure that controller is getting created inside the application context
		assertNotNull(transactionController);
	}


}
