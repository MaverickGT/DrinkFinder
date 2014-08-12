package com.example.drinkfinder;

import java.util.ArrayList;

public class Cocktail {
String name;
ArrayList<String> ingredients = new ArrayList<String>();
String mixing;

public Cocktail(){
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@SuppressWarnings("rawtypes")
public ArrayList getIngredients() {
	return ingredients;
}
public void addIngredients(String ingredient) {
	this.ingredients.add(ingredient);
}
public String getMixing() {
	return mixing;
}
public void setMixing(String mixing) {
	this.mixing = mixing;
}

@Override
public String toString() {
	StringBuffer listString = new StringBuffer();
	for(String i : ingredients){
		listString.append(i+"\n");
	}
	return name + "\n\n" + listString.toString() + "\nHow to make it: \n" + mixing ;
}

}