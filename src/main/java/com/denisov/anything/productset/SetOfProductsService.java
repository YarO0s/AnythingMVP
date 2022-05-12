package com.denisov.anything.productset;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SetOfProductsService {
    private final SetOfProductsRepository setOfProductsRepository;

    public SetOfProductsService(SetOfProductsRepository setOfProductsRepository){
        this.setOfProductsRepository = setOfProductsRepository;
    }

    public ArrayList<ProductEntity> getProductsIdByRecipe(RecipeEntity recipeEntity){
        ArrayList<ProductEntity> arrayList = new ArrayList<ProductEntity>();
        if(recipeEntity==null){
            return arrayList;
        }
        Iterable<SetOfProductsEntity> iterable = setOfProductsRepository.findByRecipeId(recipeEntity);
        Collection coll = (Collection<?>) iterable;
        for(int i = 0; i < coll.size(); i++){
            arrayList.add(iterable.iterator().next().getProductId());
        }
        return arrayList;
    }
}
