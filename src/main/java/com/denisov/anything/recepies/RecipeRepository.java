package com.denisov.anything.recepies;

import com.denisov.anything.steps.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
}
