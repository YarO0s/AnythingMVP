package com.denisov.anything.recepies;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.products.ProductService;
import com.denisov.anything.productset.SetOfProductsRepository;
import com.denisov.anything.productset.SetOfProductsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DefaultRecipeService {
    private final RecipeRepository recipeRepository;
    private final ProductService productService;
    private final SetOfProductsRepository setOfProductsRepository;
    private final SetOfProductsService setOfProductsService;

    public DefaultRecipeService(RecipeRepository recipeRepository,
                                ProductService productService,
                                SetOfProductsRepository setOfProductsRepository,
                                SetOfProductsService setOfProductsService){
        this.recipeRepository = recipeRepository;
        this.productService = productService;
        this.setOfProductsRepository = setOfProductsRepository;
        this.setOfProductsService = setOfProductsService;
    }

    public RecipeEntity getById(long id){
        RecipeEntity entity = null;
        Optional<RecipeEntity> opt = recipeRepository.findById(id);
        if(opt.isPresent()){
            entity = opt.get();
        } else{

        }
        return entity;
    }

    public ArrayList<RecipeEntity> getAll(){
        ArrayList<RecipeEntity> entities = new ArrayList<RecipeEntity>();
        recipeRepository.findAll().forEach(entities::add);
        //while(iterable.iterator().hasNext()){
        //    entities.add(iterable.iterator().next());
        //}
        System.out.println(entities);
        return entities;
    }

    public ArrayList<RecipeEntity> getAllMatchProducts(ArrayList<ProductEntity> products){
        ArrayList<RecipeEntity> result = new ArrayList<RecipeEntity>();
        ArrayList<RecipeEntity> allRecipies = getAll();
        ArrayList<ProductEntity> productsForRecipe;
        //TODO: add functionality to divide existing products and invalid ones

        for(int i = 0; i<allRecipies.size(); i++) {
            RecipeEntity recipe = allRecipies.get(i);
            productsForRecipe = setOfProductsService.getProductsIdByRecipe(recipe);
            if(productsForRecipe.size()==0 || productsForRecipe==null){
                continue;
            } else {
                if(products.containsAll(productsForRecipe)){
                    result.add(recipe);
                }
            }
        }
        return result;
    }
}
