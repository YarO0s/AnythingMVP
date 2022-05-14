package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.steps.StepEntity;

import java.util.ArrayList;

public class Recipes {

    private RecipeEntity recipeEntity;

    private String script = new String();

    private String products = new String();

    public Recipes(String script, String products, RecipeEntity recipeEntity){
        this.script = script;
        this.products = products;
        this.recipeEntity = recipeEntity;
    }

    public Recipes(){

    }

    public void setScript(String script){
        this.script = script;
    }

    public RecipeEntity getRecipeEntity(){
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity){
        this.recipeEntity = recipeEntity;
    }

    public String getScript(){
        return script;
    }

    public void setProducts(String products){
        this.products = products;
    }

    public String getProducts(){
        return products;
    }
}
