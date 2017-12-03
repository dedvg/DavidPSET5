package com.example.david.david_pset5;

/**
 * Created by dedvg on 2-12-2017.
 * with use of https://www.youtube.com/watch?v=HIm-T0NCEqY
 */
// the class uses to fill in the dish row layout with the right information
public class dish_class {
    private int id;
    private String name;
    private float price;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public dish_class(int id, String name, float price, String description, String image) {
        this.id = id;
        this.name = name;

        this.price = price;
        this.description = description;
        this.image = image;
    }

    private String image;





}
