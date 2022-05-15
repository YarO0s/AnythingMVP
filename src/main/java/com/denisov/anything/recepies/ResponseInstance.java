package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.steps.StepEntity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ResponseInstance {

    private RecipeEntity recipeEntity;
    private String script;
    private String products;

    public ResponseInstance(RecipeEntity recipeEntity, ArrayList<StepEntity> steps, ArrayList<ProductEntity> productSet){
        this.recipeEntity = recipeEntity;
        String script = "";
        for(int i = 0; i < steps.size(); i ++){
            if(i != steps.size()-1){
                script += steps.get(i).getStep() + "\n";
            } else {
                script += steps.get(i).getStep();
            }
        }
        this.script = script;
        String products = "";
        for(int i = 0; i < productSet.size(); i ++){
            if(i != productSet.size()-1){
                products += productSet.get(i).getProductName() + "\n";
            } else {
                products += productSet.get(i).getProductName();
            }
        }
        this.products = products;
    }

    public ResponseInstance(){

    }

    public String getScript(){
        return script;
    }

    public void setScript(ArrayList<StepEntity> steps){
        String script = "";
        for(int i = 0; i < steps.size(); i ++){
            if(i != steps.size()-1){
                script += steps.get(i).getStep() + "\n";
            } else {
                script += steps.get(i).getStep();
            }
        }
        this.script = script;
    }

    public String getProducts(){
        return products;
    }

    public void setProducts(ArrayList<ProductEntity> productSet){
        String products = "";
        for(int i = 0; i < productSet.size(); i ++){
            if(i != productSet.size()-1){
                products += productSet.get(i).getProductName() + "\n";
            } else {
                products += productSet.get(i).getProductName();
            }
        }
        this.products = products;
    }

    public RecipeEntity getRecipeEntity(){
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity){
        this.recipeEntity = recipeEntity;
    }
}
