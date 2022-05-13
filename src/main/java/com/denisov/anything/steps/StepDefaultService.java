package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StepDefaultService {
    private final StepRepository stepRepository;

    public StepDefaultService(StepRepository stepRepository){
        this.stepRepository = stepRepository;
    }

    public ArrayList<StepEntity> getStepsByRecipe(RecipeEntity recipeEntity){
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        Iterable<StepEntity> iterable = stepRepository.findByRecipeId(recipeEntity);
        Collection coll = (Collection<?>) iterable;
        for(int i = 0; i < coll.size(); i++){
            steps.add(iterable.iterator().next());
        }
        return steps;
    }
}
