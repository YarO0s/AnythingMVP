package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;
import com.denisov.anything.recepies.RecipeRepository;
import org.json.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="steps")
public class StepController {
    private final StepRepository stepRepository;
    private final RecipeRepository recipeRepository;

    public StepController(StepRepository stepRepository,
                          RecipeRepository recipeRepository){
        this.stepRepository = stepRepository;
        this.recipeRepository = recipeRepository;
    }

    @PostMapping("/new")
    public String newStep(@RequestParam String step,
                        @RequestParam long id){
        StepEntity stepEntity = new StepEntity();
        RecipeEntity recipeEntity = null;
        JSONObject result = new JSONObject();
        try {
            recipeEntity = recipeRepository.findById(id).get();
        } catch(Exception e){
            e.printStackTrace();
            result.put("result", "error: correlated recipe not found");
        }
        if(recipeEntity == null){
            result.put("result", "error: correlated recipe not found");
            return result.toString();
        }
        stepEntity.setRecipeId(recipeEntity);
        stepEntity.setStep(step);
        stepRepository.save(stepEntity);
        result.put("result", "successful:");
        return result.toString();
    }
}
