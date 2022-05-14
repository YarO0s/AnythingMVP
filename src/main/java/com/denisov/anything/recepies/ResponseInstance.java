package com.denisov.anything.recepies;

import java.util.ArrayList;

public class ResponseInstance {

    private String result;

    private ArrayList<Recipes> recipes;

    public ResponseInstance(String result, ArrayList<Recipes> recipes){
        this.result = result;
        this.recipes = recipes;
    }

    public ResponseInstance(){

    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipes> recipes) {
        this.recipes = recipes;
    }
}
