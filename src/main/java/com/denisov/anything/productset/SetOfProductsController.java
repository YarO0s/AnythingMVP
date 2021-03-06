package com.denisov.anything.productset;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.products.ProductRepository;
import com.denisov.anything.recepies.RecipeEntity;
import com.denisov.anything.recepies.RecipeRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productset")
public class SetOfProductsController {
    //TODO: replace with service
    private final SetOfProductsRepository setOfProductsRepository;
    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;

    public SetOfProductsController(SetOfProductsRepository setOfProductsRepository,
                                   ProductRepository productRepository,
                                   RecipeRepository recipeRepository){
        this.setOfProductsRepository = setOfProductsRepository;
        this.productRepository = productRepository;
        this.recipeRepository = recipeRepository;
    }

    @PostMapping("new")
    public String saveProductset(@RequestParam long productId, @RequestParam long recipeId){
        JSONObject result = new JSONObject().put("result: ", "successful: ");
        long id = 0;
        try {
            SetOfProductsEntity setOfProductsEntity = new SetOfProductsEntity();
            ProductEntity productEntity = productRepository.findById(productId).get();
            RecipeEntity recipeEntity = recipeRepository.findById(recipeId).get();
            setOfProductsEntity.setProductId(productEntity);
            setOfProductsEntity.setRecipeId(recipeEntity);
            setOfProductsRepository.save(setOfProductsEntity);
            id = setOfProductsEntity.getId();
        } catch (Exception e){
            e.printStackTrace();
            result.put("result", "error: ");
            return result.toString();
        }
        result.put("result", "successful: generated id " + id);
        return result.toString();
    }
}
