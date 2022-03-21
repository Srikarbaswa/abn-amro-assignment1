package com.abnamro.RecipeAssignment.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.abnamro.RecipeAssignment.model.Recipe;
import com.abnamro.RecipeAssignment.repository.RecipeRepository;
import com.abnamro.RecipeAssignment.service.RecipeInterface;
import com.google.gson.Gson;


@Service("recipeInterface")
public class RecipeServiceImpl implements RecipeInterface 
{
	@Autowired
	public RecipeRepository recipeRepository;

	//Logic to get all dishes recipes
	public ResponseEntity<List<Recipe>> getAllRecipes(String dishname) {
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
	//Logic to get a particular dish recipe using ID
	public ResponseEntity<Recipe> getRecipeById(long id) {
		Optional<Recipe> RecipeData = recipeRepository.findById(id);
		if (RecipeData.isPresent()) {
			return new ResponseEntity<>(RecipeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Logic to create a new dish recipes
	public ResponseEntity<Recipe> createRecipe(String recipe1) {
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
	//Logic to update the different fields or  an existing recipe
	public ResponseEntity<Recipe> updateRecipe(String recipe2) {
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
	//To delete a dish recipe using their ID 
	public ResponseEntity<HttpStatus> deleteRecipe(long id) {
		try {
			recipeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
