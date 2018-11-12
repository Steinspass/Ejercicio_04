package com.example.ndpsh.ejercicio_04.Models;



import com.example.ndpsh.ejercicio_04.Applications.Apps;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class City extends RealmObject {

    @PrimaryKey
    private int Id;
    @Required
    private String Name;
    @Required
    private String specs;
    private String image;
    private float stars;

    public City(){

    }

    public City (String Name, String specs, String image, float stars){
        this.Id = Apps.CityID.incrementAndGet();
        this.Name = Name;
        this.specs = specs;
        this.image = image;
        this.stars = stars;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
