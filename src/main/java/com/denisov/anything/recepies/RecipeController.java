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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="recipe", produces="application/json")
public class RecipeController {

    private final DefaultRecipeService recipeService;
    private final ProductService productService;
    private final StepDefaultService stepService;
    private final ProductRepository productRepository;
    private final SetOfProductsService setOfProductsService;
    private final RecipeRepository recipeRepository;

    public RecipeController(DefaultRecipeService recipeService,
                            ProductService productService,
                            StepDefaultService stepService,
                            ProductRepository productRepository,
                            SetOfProductsService setOfProductsService,
                            RecipeRepository recipeRepository){
        this.recipeService = recipeService;
        this.productService = productService;
        this.stepService = stepService;
        this.productRepository = productRepository;
        this.setOfProductsService = setOfProductsService;
        this.recipeRepository = recipeRepository;
    }

    //TODO: remove all logic from controller to service
    @GetMapping("/getRecipe/byId")
    public String getRecipeById(@RequestParam long id){
        //TODO: create JSON and return with steps and products lists
        ResponseInstance responseInstance = new ResponseInstance();
        JSONArray resultArray = new JSONArray();
        JSONObject j = new JSONObject();
        RecipeEntity entity = recipeService.getById(id);
        if(entity == null){
            responseInstance.setResult("error: recipe not found");
            j.put("result", "error: recipe not found");
            j.put("recipe", "");
            return j.toString();
        } else {
            ArrayList<RecipeEntity> recipeEntity = new ArrayList<RecipeEntity>();
            j.put("result", "successful: ");
            j.put("name", entity.getName());


            ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
            steps = stepService.getStepsByRecipe(entity);
            if(steps.isEmpty() || steps.size()==0){
                j.put("script", "Script for cooking haven't been added yet :(");
            }
            String script = "";
            for(int i = 0; i < steps.size(); i++){
                script += steps.get(i).getStep() + " ";
            }
            j.put("script", script.trim());


            ArrayList<ProductEntity> p = setOfProductsService.getProductsIdByRecipe(entity);
            if(p.size()==0){
                j.put("products", "Products set haven't been added yet :(");
            }
            String productsResult = "";
            for(ProductEntity productEntity: p){
                productsResult += productEntity.getProductName() + " ";
            }
            j.put("products", productsResult.trim());
            return j.toString();
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
        //responseInstance.setRecipes(entities);
        return new Gson().toJson(responseInstance);
    }

    @GetMapping("/getRecipes/searchByProducts")
    public String getRecipesByProducts(@RequestParam String productsArray){
        //JSONObject result = new JSONObject();
        String res;
        String[] products = productsArray.split(",");

        ResponseInstance response = new ResponseInstance();

        ArrayList<ProductEntity> productEntities = productService.selectProductsByName(products);

        ArrayList<RecipeEntity> recipes = new ArrayList<RecipeEntity>();
        /*boolean zeroProductsError = false;
        if(productEntities.size()==0 || productEntities == null){
            response.setResult("error: products not found");

            res = new Gson().toJson(response);
            zeroProductsError = true;
        }*/

        recipes = recipeService.getAllMatchProducts(productEntities);
        ArrayList<Recipes> arr = new ArrayList<Recipes>();
        for(int i = 0; i < recipes.size(); i++){
            ArrayList<StepEntity> script = new ArrayList<StepEntity>();
            ArrayList<ProductEntity> product = new ArrayList<ProductEntity>();
            script = stepService.getStepsByRecipe(recipes.get(i));
            product = productService.selectProductsByName(products);
            String s = "";
            for(int j = 0; j < script.size(); j++){
                if(j != (script.size()-1)) {
                    s += script.get(i).getStep() + "\n";
                } else{
                    s += script.get(i).getStep();
                }
            }
            String p = "";
            for(int j = 0; j < product.size(); j++){
                if(j !=(product.size() - 1)){
                    p += product.get(i).getProductName() + "\n";
                } else {
                    p += product.get(i).getProductName();
                }
            }

            Recipes recipe = new Recipes(s, p, recipes.get(i));
            arr.add(recipe);
        }
        response.setResult("successful: ");
        response.setRecipes(arr);

        return new Gson().toJson(response);

    }

    @PostMapping("/new")
    public String saveRecipe(@RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "url") String url){
        JSONObject jsonObject = new JSONObject().put("result", "successful: ");
        long id = 0;
        try {
            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setName(name);
            recipeEntity.setDescription(description);
            recipeEntity.setUrl(url);
            recipeRepository.save(recipeEntity);
            id = recipeEntity.getId();
        } catch(Exception e){
            e.printStackTrace();
            jsonObject.put("result", "error: unknown error");
            return jsonObject.toString();
        }
        jsonObject.put("result", "successful: generated id " + id);
        return jsonObject.toString();
    }
}
