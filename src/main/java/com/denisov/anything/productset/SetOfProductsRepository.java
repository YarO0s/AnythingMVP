package com.denisov.anything.productset;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SetOfProductsRepository extends CrudRepository<SetOfProductsEntity, Long> {
    public Iterable<SetOfProductsEntity> findByRecipeId(RecipeEntity recipeEntity);
}
