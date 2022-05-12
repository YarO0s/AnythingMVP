package com.denisov.anything.products;

import com.denisov.anything.recepies.RecipeEntity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private long id;

    @Column(name = "product_name")
    private String name;

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public ProductEntity(String name){
        this.name = name;
    }

    public  ProductEntity(){

    }

    public String toString(){
        return id + " " + name + " ";
    }
}
