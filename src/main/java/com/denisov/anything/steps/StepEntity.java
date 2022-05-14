package com.denisov.anything.steps;

import com.denisov.anything.recepies.RecipeEntity;

import javax.persistence.*;

@Entity
@Table(name="set_of_steps")
public class StepEntity {

    @SequenceGenerator(
            name = "appUserSequence",
            sequenceName = "appUserSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appUserSequence")
    @Id
    private long id;
    @Column(name="step", unique=false, nullable = false)
    private String step;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable =true)
    private RecipeEntity recipeId;

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
    }

    public StepEntity(String step, RecipeEntity recipeId) {
        this.step = step;
        this.recipeId = recipeId;
    }

    public StepEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
