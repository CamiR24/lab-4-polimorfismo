package com.biblioteca.bean;

public class ResourcesLibrary {

    private String name;
    private String ISBN;

    private String type;

    private double price;

    public ResourcesLibrary(String name, String ISBN, String type, double price) {
        this.name = name;
        this.ISBN = ISBN;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nNombre: " + name + " ISBN: " + ISBN + "Tipo: " + type + " Precio: " + price;
    }
}
