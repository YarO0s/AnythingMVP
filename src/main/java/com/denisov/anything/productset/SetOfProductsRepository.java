package com.denisov.anything.productset;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

public interface SetOfProductsRepository extends CrudRepository<SetOfProductsEntity, Long> {
    public Iterable<SetOfProductsEntity> findByRecipeId(RecipeEntity recipeEntity);
}
