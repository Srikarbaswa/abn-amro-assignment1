package com.abnamro.RecipeAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abnamro.RecipeAssignment.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findBydishname(String dishname);

}
