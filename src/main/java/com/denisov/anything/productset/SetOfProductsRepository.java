package com.denisov.anything.productset;

import com.denisov.anything.steps.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

public interface SetOfProductsRepository extends CrudRepository<SetOfProductsEntity, Long> {
    public Iterable<SetOfProductsEntity> findByRecipeId(RecipeEntity recipeEntity);
}
