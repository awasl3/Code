package com.example.simpleapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleAppTests {

	//Only used for test coverage 
	@Test
	void contextLoads() {
		SimpleApp.main(new String[] {});
	}

}
