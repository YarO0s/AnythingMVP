package com.example.anything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Recepeis_activity extends AppCompatActivity {

    ImageView searchIcon;
    EditText searchText;

    ImageView profile;

    RecyclerView mRecyclerView;
    ArrayList<RecipesData> recipesList;
    RecipesData mRecipesData;
    private RecipeAdapter recipeAdapter;
    private ProgressBar loadingProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepeis);
        loadingProgressBar = findViewById((R.id.idPBLoading));
        recipesList = new ArrayList<RecipesData>();
        recipeAdapter = new RecipeAdapter(Recepeis_activity.this,recipesList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recipes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Recepeis_activity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(recipeAdapter);

        ArrayList<String> pickedProducts = (ArrayList<String>)getIntent().getSerializableExtra("pickedProducts");
        String productsStr = "";
        for(int i = 0; i < pickedProducts.size(); i++)
        {
            if(i != (pickedProducts.size()-1)){
            productsStr += pickedProducts.get(i) +",";
            } else {
            productsStr += pickedProducts.get(i);
            }
        }

        sendReq(productsStr);




//        // Функционал при нажатии на кнопку поиска
//        searchIcon = (ImageView) findViewById(R.id.searchIcon);
//        searchText = (EditText) findViewById(R.id.searchText);
//        searchIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchText.setVisibility(View.VISIBLE);
//            }
//        });

        // Функционал при нажатии на кнопку профиля
        profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recepeis_activity.this,ProductList_activity.class);
                intent.putExtra("pickedProductsNew",pickedProducts);
                startActivity(intent);
            }
        });

    }

    public void sendReq(String products){
        // Запрос рецептов
        String url = "https://anything-app.herokuapp.com/recipe/getRecipes/searchByProducts?productsArray=" + products;
        RequestQueue requestQueue = Volley.newRequestQueue(Recepeis_activity.this);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                recipesList.clear();
                loadingProgressBar.setVisibility(View.GONE);

                try {
                    JSONArray recipes = response.getJSONArray("recipes");
                    for(int i = 0; i < recipes.length(); i++){
                        JSONObject recipe = recipes.getJSONObject(i);
                        String name = recipe.getJSONObject("recipeEntity").getString("name");
                        String ingredients = recipe.getString("products");
                        String instruction = recipe.getString("script");
                        String url = recipe.getJSONObject("recipeEntity").getString("url");
                        recipesList.add(new RecipesData(name,ingredients,instruction,url));
                    }
                    if(recipesList.isEmpty()==true) Toast.makeText(getApplicationContext(),"No recipes found",Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }recipeAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Request error",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObject);
    }
}