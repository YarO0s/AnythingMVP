package com.example.anything;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivityRecipe extends AppCompatActivity {

    TextView recipeTitle, recipeIngredients, recipeDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        recipeTitle = (TextView) findViewById(R.id.recipe_detail_title);
        recipeIngredients = (TextView) findViewById(R.id.recipe_detail_ingredients);
        recipeDescription = (TextView) findViewById(R.id.recipe_detail_description);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null){
            recipeTitle.setText(mBundle.getString("Title"));
            recipeIngredients.setText(mBundle.getString("Ingredients"));
            recipeDescription.setText(mBundle.getString("Description"));
        }
    }
}