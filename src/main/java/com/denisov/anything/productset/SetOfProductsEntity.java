package com.denisov.anything.productset;

import com.denisov.anything.products.ProductEntity;
import com.denisov.anything.recepies.RecipeEntity;

import javax.persistence.*;

@Entity
@Table(name = "set_of_products")
public class SetOfProductsEntity {

    @SequenceGenerator(
            name = "productsSetSequence",
            sequenceName = "productsSetSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "productsSetSequence")
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity productId;

    public SetOfProductsEntity(ProductEntity productId, RecipeEntity recipeId) {
        this.productId = productId;
        this.recipeId = recipeId;
    }

    public SetOfProductsEntity(){
        
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName="id", nullable = false)
    private RecipeEntity recipeId;

    public ProductEntity getProductId() {
        return productId;
    }

    public void setProductId(ProductEntity productId) {
        this.productId = productId;
    }

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
    }

    public long getId(){
        return id;
    }
}
