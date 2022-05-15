package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.products.ProductRepository;
import com.denisov.anything.products.ProductService;
import com.denisov.anything.productset.SetOfProductsService;
import com.denisov.anything.steps.StepDefaultService;
import com.denisov.anything.steps.StepEntity;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="recipe", produces="application/json")
public class RecipeController {

    private final DefaultRecipeService recipeService;
    private final ProductService productService;
    private final StepDefaultService stepService;
    private final SetOfProductsService setOfProductsService;

    public RecipeController(DefaultRecipeService recipeService,
                            ProductService productService,
                            StepDefaultService stepService,
                            SetOfProductsService setOfProductsService){
        this.recipeService = recipeService;
        this.productService = productService;
        this.stepService = stepService;
        this.setOfProductsService = setOfProductsService;
    }

    //TODO: remove all logic from controller to service
    @GetMapping("/getRecipe/byId")
    public String getRecipeById(@RequestParam long id){
        //TODO: create JSON and return with steps and products lists
        JSONObject result = new JSONObject();
        RecipeEntity recipe = recipeService.getById(id);
        if(recipe == null){
            result.put("result", "error: recipes not found");
            return result.toString();
        }
        ArrayList<ProductEntity> products = setOfProductsService.getProductsIdByRecipe(recipe);
        ArrayList<StepEntity> script = stepService.getStepsByRecipe(recipe);
        result.put("result", "successful");
        result.put("recipe", new ResponseInstance(recipe, script, products));
        return result.toString();
    }

    @GetMapping("/getRecipe")
    public String getRecipes(){
        ArrayList<RecipeEntity> entities = null;
        JSONObject result = new JSONObject();
        entities = recipeService.getAll();
        if (entities == null){
            result.put("result", "error: recipes not found");
            return result.toString();
        }
        ArrayList<ResponseInstance> response = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++){
           ArrayList<ProductEntity> products = setOfProductsService.getProductsIdByRecipe(entities.get(i));
           ArrayList<StepEntity> steps = stepService.getStepsByRecipe(entities.get(i));
           response.add(new ResponseInstance(entities.get(i), steps, products));
        }
        result.put("result", "successful: ");
        result.put("recipes", response);
        return result.toString();
    }

    @GetMapping("/getRecipes/searchByProducts")
    public String getRecipesByProducts(@RequestParam String productsArray){
        JSONObject result = new JSONObject();
        ArrayList<ResponseInstance> responseArray = null;
        try {
            ArrayList<ProductEntity> products = productService.selectProductsByName(productsArray.split(","));
            ArrayList<RecipeEntity> recipes = recipeService.getAllMatchProducts(products);
            responseArray = new ArrayList<ResponseInstance>();
            for (RecipeEntity recipe : recipes) {
                ArrayList<ProductEntity> requiredProducts = setOfProductsService.getProductsIdByRecipe(recipe);
                ArrayList<StepEntity> script = stepService.getStepsByRecipe(recipe);
                responseArray.add(new ResponseInstance(recipe, script, requiredProducts));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.put("result", "error: unknown");
            return result.toString();
        }
        result.put("result", "successful: ");
        result.put("recipes", responseArray);

        return result.toString();
    }

    @PostMapping("/new")
    public String saveRecipe(@RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "url") String url,
                             @RequestParam(name = "products") String products,
                             @RequestParam(name = "script") String script){
        JSONObject result = new JSONObject();
        ArrayList<ProductEntity> productEntities = null;
        long id = 0;

        try {
            if(products != "" && products != null) {
                String[] productSet = products.split(",");
                productEntities = productService.selectProductsByName(productSet);
                if (productEntities == null || productEntities.size() == 0) {
                    result.put("result: ", "error: specified products does not exist");
                    return result.toString();
                }

            }


            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setName(name);
            recipeEntity.setDescription(description);
            recipeEntity.setUrl(url);
            recipeService.save(recipeEntity);
            id = recipeEntity.getId();

            for (ProductEntity product : productEntities) {
                setOfProductsService.addProductsToRecipe(recipeEntity, product);
            }

            if(script != "" && script != null){
                String[] steps = script.split(",");
                for(String step : steps) {
                    stepService.save(new StepEntity(step, recipeEntity));
                }
            }

        } catch(Exception e){
            e.printStackTrace();
            result.put("result", "error: unknown error");
            return result.toString();
        }


        result.put("result", "successful: generated id " + id);
        return result.toString();
    }
}
