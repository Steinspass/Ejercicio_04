package com.example.ndpsh.ejercicio_04.Models;



import io.realm.RealmList;
import io.realm.RealmObject;

public class City extends RealmObject {


    private int Id;
    private String Name;
    private String specs;
    private int imgback;
    private RealmList<City> cities;

    public City (String Name){
        this.Id = 0;
        this.Name = Name;
        this.specs = specs;
        this.imgback = imgback;
        this.cities = new RealmList<City>();
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

    public int getImgback() {
        return imgback;
    }

    public void setImgback(int imgback) {
        this.imgback = imgback;
    }

    public RealmList<City> getCities() {
        return cities;
    }
}
