package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.products.ProductService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("recipe")
public class RecipeController {

    private final DefaultRecipeService recipeService;
    private final ProductService productService;


    public RecipeController(DefaultRecipeService recipeService,
                            ProductService productService){
        this.recipeService = recipeService;
        this.productService = productService;
    }

    @GetMapping("/getRecipe/byId")
    public String getRecipeById(@RequestParam long id){
        //TODO: create JSON and return with steps and products lists
        ResponseInstance responseInstance = new ResponseInstance();
        RecipeEntity entity = recipeService.getById(id);
        if(entity == null){
            responseInstance.setResult("error: recipe not found");
            return new Gson().toJson(responseInstance);
        } else {
            ArrayList<RecipeEntity> recipeEntity = new ArrayList<RecipeEntity>();
            recipeEntity.add(entity);
            responseInstance.setRecipes(recipeEntity);
            responseInstance.setResult("successful: ");
            return new Gson().toJson(responseInstance);
        }
    }

    @GetMapping("/getRecipe")
    public String getRecipes(){
        ArrayList<RecipeEntity> entities = null;
        ResponseInstance responseInstance = new ResponseInstance();
        JSONObject jsonObject = new JSONObject();
        entities = recipeService.getAll();
        if (entities == null){
            responseInstance.setResult("error: service did not return recipies array");
            String result = new Gson().toJson(responseInstance);
            return result;
        }
        responseInstance.setResult("successful: ");
        responseInstance.setRecipes(entities);
        return new Gson().toJson(responseInstance);
    }

    @GetMapping("/getRecipes/searchByProducts")
    public String getRecipesByProducts(@RequestParam String productsArray){
        //JSONObject result = new JSONObject();
        String result;
        String[] products = productsArray.split(",");
        ResponseInstance response = new ResponseInstance();
        ArrayList<ProductEntity> productEntities = productService.selectProductsByName(products);
        ArrayList<RecipeEntity> recipes = new ArrayList<RecipeEntity>();
        boolean zeroProductsError = false;
        if(productEntities.size()==0 || productEntities == null){
            response.setResult("error: products not found");
            response.setProducts(null);
            result = new Gson().toJson(response);
            zeroProductsError = true;
        }

        recipes = recipeService.getAllMatchProducts(productEntities);

        if(recipes.size()==0 || recipes == null){
            if(zeroProductsError == false) {
                response.setResult("error: recipes not found");
            } else {
                response.setResult(response.getResult() + ",  recipes not found");
            }
            response.setRecipes(null);
            result = new Gson().toJson(response);
        }
        response.setResult("successful: ");
        response.setRecipes(recipes);
        response.setProducts(null);
        Gson gson = new Gson();
        result = gson.toJson(response);

        System.out.println(result);
        return result;
    }
}
