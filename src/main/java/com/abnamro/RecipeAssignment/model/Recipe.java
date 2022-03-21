package com.abnamro.RecipeAssignment.model;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//Table and entity declaration
@Entity
@Table(name = "Recipes")
public class Recipe {
	//Coloumns in table 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "Time")
	private String time;
	@Column(name = "dishname")
	private String dishname;
	@Column(name = "dishtype")
	private String dishtype;
	@Column(name = "serves")
	private String serves;
	@Column(name = "ingredients")
	private String ingredients;
	//Constructors
	public Recipe(long id, String time, String dishname, String dishtype, String serves, String ingredients) {
		super();
		this.id = id;
		this.time = LocalTime.now().toString();
		this.dishname = dishname;
		this.dishtype = dishtype;
		this.serves = serves;
		this.ingredients = ingredients;
	}
	
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Recipe(String dishname, String dishtype) {
		this.dishname = dishname;
		this.dishtype = dishtype;
	}
	//Getters and setters
	public Recipe() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDishname() {
		return dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}

	public String getDishtype() {
		return dishtype;
	}

	public void setDishtype(String dishtype) {
		this.dishtype = dishtype;
	}

	public String getServes() {
		return serves;
	}

	public void setServes(String serves) {
		this.serves = serves;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + dishname + ", desc=" + dishtype + ", published=" + serves + "]";
	}
}
