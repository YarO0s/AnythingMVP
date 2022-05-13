package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface StepRepository extends CrudRepository<StepEntity, Long> {
    public Iterable<StepEntity> findByRecipeId(RecipeEntity recipeEntity);
}
