package com.denisov.anything.steps;

import org.springframework.data.repository.CrudRepository;

public interface StepRepository extends CrudRepository<StepEntity, Long> {
    public Iterable<StepEntity> findByRecipeId(RecipeEntity recipeEntity);
}
