package com.example.anything;

import java.util.ArrayList;

public class RecipesData {

    private String itemName;
    private String itemIngredients;
    private String itemDescription;
    private String itemImage;

    public RecipesData(String itemName, String itemIngredients, String itemDescription, String itemImage) {
        this.itemName = itemName;
        this.itemIngredients = itemIngredients;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemIngredients() {
        return itemIngredients;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemIngredients(String itemIngredients) {
        this.itemIngredients = itemIngredients;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
