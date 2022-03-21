package com.abnamro.RecipeAssignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


import com.abnamro.RecipeAssignment.controllers.RecipeController;

@SpringBootTest
public class SmokeTest {
	@Autowired
	private RecipeController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
