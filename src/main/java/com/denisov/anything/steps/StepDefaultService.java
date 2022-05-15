package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class StepDefaultService {
    private final StepRepository stepRepository;

    public StepDefaultService(StepRepository stepRepository){
        this.stepRepository = stepRepository;
    }

    public ArrayList<StepEntity> getStepsByRecipe(RecipeEntity recipeEntity){
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        Iterator<StepEntity> script = stepRepository.findByRecipeId(recipeEntity).iterator();
        while(script.hasNext()){
            steps.add(script.next());
        }
        return steps;
    }

    public void save(StepEntity step){
        stepRepository.save(step);
    }
}
