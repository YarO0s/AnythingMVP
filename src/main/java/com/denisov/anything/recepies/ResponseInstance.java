package com.denisov.anything.recepies;

import com.denisov.anything.steps.RecipeEntity;

import java.util.ArrayList;

public class ResponseInstance {

    private String result;

    private ArrayList<RecipeEntity> recipes;

    private ArrayList<String> products;

    public ResponseInstance(String result, ArrayList<RecipeEntity> recipes, ArrayList<String> products){
        this.result = result;
        this.recipes = recipes;
        this.products = products;
    }

    public ResponseInstance(){

    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<String> getProducts(){
        return products;
    }

    public void setProducts(ArrayList<String> products){
        this.products = products;
    }
}
