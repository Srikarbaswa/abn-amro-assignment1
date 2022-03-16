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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RecipeController {
	@Autowired
	public RecipeRepository recipeRepository;

	@GetMapping("/Recipes/getAllRecipes/")
	public ResponseEntity<List<Recipe>> getAllRecipes(@RequestParam(required = false) String dishname) {
		try {
			List<Recipe> Recipes = new ArrayList<Recipe>();
			if (dishname == null)
				recipeRepository.findAll().forEach(Recipes::add);
			else
				recipeRepository.findBydishname(dishname).forEach(Recipes::add);
			if (Recipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Recipes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Recipes/getRecipebyID/")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
		Optional<Recipe> RecipeData = recipeRepository.findById(id);
		if (RecipeData.isPresent()) {
			return new ResponseEntity<>(RecipeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Recipes/addNewRecipe/")
	public ResponseEntity<Recipe> createRecipe(@RequestBody String recipe1) {
		try {
			Gson gson = new Gson();
			Recipe recipe = gson.fromJson(recipe1, Recipe.class);
			Recipe create = recipeRepository.save(new Recipe(recipe.getId(), recipe.getTime(), recipe.getDishname(),
					recipe.getDishtype(), recipe.getServes(), recipe.getIngredients()));
			return new ResponseEntity<>(create, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Recipes/updateRecipe/")
	public ResponseEntity<Recipe> updateRecipe(@RequestBody String recipe2) {
		try {
			Gson gson = new Gson();
			Recipe recipe = gson.fromJson(recipe2, Recipe.class);
			Recipe update = recipeRepository.save(new Recipe(recipe.getId(), recipe.getTime(), recipe.getDishname(),
					recipe.getDishtype(), recipe.getServes(), recipe.getIngredients()));
			return new ResponseEntity<>(update, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Recipes/deleteRecipeByID/")
	public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") long id) {
		try {
			recipeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
