package com.stellar.burgers.model;

public class Ingredient {
    private String _id;
    private String name;
    private String type;
    private float price;

    public Ingredient() {
    }

    public Ingredient(String _id, String name, String type, float price) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
