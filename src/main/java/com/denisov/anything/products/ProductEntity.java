package com.denisov.anything.products;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @SequenceGenerator(
            name = "productSequence",
            sequenceName = "productSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "productSequence")
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

    public long getId(){
        return id;
    }
}
