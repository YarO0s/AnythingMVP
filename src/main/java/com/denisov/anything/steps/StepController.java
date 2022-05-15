package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;
import com.denisov.anything.recepies.RecipeRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
@RequestMapping(value="steps", produces="text/json")
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
        System.out.println("st");
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
        stepRepository.save(new StepEntity(step, recipeEntity));
        result.put("result", "successful:");
        return result.toString();
    }

    @GetMapping("/selectAll")
    public String selectAllSteps(){
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        Iterable<StepEntity> iterableSteps = stepRepository.findAll();
        Iterator<StepEntity> stepsIterator = iterableSteps.iterator();
        while(stepsIterator.hasNext()){
            steps.add(stepsIterator.next());
        }
        JSONObject result = new JSONObject();
        result.put("result", "successful: ");
        result.put("steps", steps);
        return result.toString();
    }

}
