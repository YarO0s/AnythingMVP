package com.example.anything;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder>{

    private Context mContext;
    private List<RecipesData>  recipesList;

    public RecipeAdapter(Context mContext, List<RecipesData> recipesList) {
        this.mContext = mContext;
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        RecipesData list = recipesList.get(position);
        // Превью рецепта
        Picasso.get().load(list.getItemImage()).into(holder.imageView);
//        holder.imageView.setImageResource(recipesList.get(position).getItemImage());
        holder.mTitle.setText(list.getItemName());

        // Детальный рецепт
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivityRecipe.class);
                intent.putExtra("Title",recipesList.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("Ingredients",recipesList.get(holder.getAdapterPosition()).getItemIngredients());
                intent.putExtra("Description",recipesList.get(holder.getAdapterPosition()).getItemDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return recipesList.size();}
}
class RecipeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mTitle;
    TextView mIngredients;
    TextView mDescription;
    CardView mCardView;

    public RecipeViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.recipe_item_image);
        mTitle = itemView.findViewById(R.id.recipe_item_title);
        mIngredients = itemView.findViewById(R.id.recipe_detail_ingredients);
        mDescription = itemView.findViewById(R.id.recipe_detail_ingredients);

        mCardView = itemView.findViewById(R.id.recipe_item);


    }
}
