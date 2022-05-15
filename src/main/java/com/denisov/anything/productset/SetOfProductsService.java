package com.denisov.anything.productset;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class SetOfProductsService {
    private final SetOfProductsRepository setOfProductsRepository;

    public SetOfProductsService(SetOfProductsRepository setOfProductsRepository){
        this.setOfProductsRepository = setOfProductsRepository;
    }

    public ArrayList<ProductEntity> getProductsIdByRecipe(RecipeEntity recipeEntity){
        ArrayList<ProductEntity> requiredProducts = new ArrayList<ProductEntity>();
        if(recipeEntity==null){
            return requiredProducts;
        }
        Iterator<SetOfProductsEntity> iterable = setOfProductsRepository.findByRecipeId(recipeEntity).iterator();
        while(iterable.hasNext()){
            requiredProducts.add(iterable.next().getProductId());
        }
        return requiredProducts;
    }

    public void addProductsToRecipe(RecipeEntity recipe, ProductEntity product){
        SetOfProductsEntity setOfProductsEntity = new SetOfProductsEntity(product, recipe);
        setOfProductsRepository.save(setOfProductsEntity);
    }
}
