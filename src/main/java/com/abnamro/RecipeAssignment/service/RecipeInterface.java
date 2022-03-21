package com.abnamro.RecipeAssignment.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abnamro.RecipeAssignment.model.Recipe;

//List of all interfaces for using business logic in controllers
public interface RecipeInterface 
{
	public ResponseEntity<List<Recipe>> getAllRecipes(String dishname);
	public ResponseEntity<Recipe> getRecipeById(long id);
	public ResponseEntity<Recipe> createRecipe(String recipe1);
	public ResponseEntity<Recipe> updateRecipe(String recipe2);
	public ResponseEntity<HttpStatus> deleteRecipe(long id);
}
