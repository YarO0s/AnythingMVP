package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.steps.StepEntity;

import java.util.ArrayList;

public class Recipes {

    private RecipeEntity recipeEntity;

    private ArrayList<StepEntity> script = new ArrayList<StepEntity>();

    private ArrayList<ProductEntity> products = new ArrayList<ProductEntity>();

    public Recipes(ArrayList<StepEntity> script, ArrayList<ProductEntity> products, RecipeEntity recipeEntity){
        this.script = script;
        this.products = products;
        this.recipeEntity = recipeEntity;
    }

    public Recipes(){

    }

    public void setScript(ArrayList<StepEntity> script){
        this.script = script;
    }

    public RecipeEntity getRecipeEntity(){
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity){
        this.recipeEntity = recipeEntity;
    }

    public ArrayList<StepEntity> getScript(){
        return script;
    }

    public void setProducts(ArrayList<ProductEntity> products){
        this.products = products;
    }

    public ArrayList<ProductEntity> getProducts(){
        return products;
    }
}
