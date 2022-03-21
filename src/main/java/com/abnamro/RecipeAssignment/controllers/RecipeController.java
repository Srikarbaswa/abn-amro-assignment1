package com.abnamro.RecipeAssignment.controllers;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.RecipeAssignment.model.Recipe;
import com.abnamro.RecipeAssignment.repository.RecipeRepository;
import com.abnamro.RecipeAssignment.service.RecipeInterface;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RecipeController {
	@Autowired
	public RecipeRepository recipeRepository;
	
	//Interfaceobject creation
	@Autowired
	public RecipeInterface recipeInterface;
	
	//To get all recipes present
	@GetMapping("/Recipes/dishes-list/")
	public ResponseEntity<List<Recipe>> getAllRecipes(@RequestParam(required = false) String dishname) {
		try {
			return recipeInterface.getAllRecipes(dishname);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//To find a recipe by their Id
	@GetMapping("/Recipes/dish-byId/")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id)  {
	try {
		return recipeInterface.getRecipeById(id);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		}
	//To add a new recipe
	@PostMapping("/Recipes/new-dish/")
	public ResponseEntity<Recipe> createRecipe(@RequestBody String recipe1) {
		try {
			return recipeInterface.createRecipe(recipe1);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//To update a existing recipe
	@PutMapping("/Recipes/Update-Dish/")
	public ResponseEntity<Recipe> updateRecipe(@RequestBody String recipe2) {
		try {
			return recipeInterface.updateRecipe(recipe2);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	//To delete a recipe with using their ID
	@DeleteMapping("/Recipes/delete-dish-recipe-ID/")
	public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") long id) {
		try {
			return recipeInterface.deleteRecipe(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
