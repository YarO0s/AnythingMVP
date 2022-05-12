package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;

import java.util.ArrayList;

public class StepDefaultService {
    private final StepRepository stepRepository;

    public StepDefaultService(StepRepository stepRepository){
        this.stepRepository = stepRepository;
    }

    public ArrayList<StepEntity> getStepsByRecipe(RecipeEntity recipeEntity){
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        Iterable<StepEntity> iterable = stepRepository.findByRecipeId(recipeEntity);
        while(iterable.iterator().hasNext()){
            steps.add(iterable.iterator().next());
        }
        return steps;
    }
}
