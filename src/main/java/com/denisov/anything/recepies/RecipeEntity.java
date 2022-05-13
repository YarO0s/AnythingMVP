package com.denisov.anything.recepies;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name ="recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @Column(name = "title", nullable = false, unique = true)
    private String name;
    @Column(name = "image_url", nullable = true, unique = false)
    private String url;
    @Column(name= "description", nullable = true, unique = false)
    private String description;

    public RecipeEntity(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;

    }

    public RecipeEntity(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public String toString(){
        return ""+ this.id + " " + this.name + " " + this.description;
    }
}
