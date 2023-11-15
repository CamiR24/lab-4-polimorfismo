package com.biblioteca.bean;

import java.util.ArrayList;

import com.biblioteca.inter.ProfileInterface;

public abstract class User implements ProfileInterface{
    protected String name;
    protected String password;
    protected String user;
    protected int books;

    protected ArrayList<ResourcesLibrary> loan = new ArrayList<ResourcesLibrary>();

    public User(String name, String password, String user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return user;
    }

    public void setID(String ID) {
        this.user = ID;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books){
        this.books = books;
    }

    public ArrayList<ResourcesLibrary> getLoan() {
        return loan;
    }

    public void setLoan(ArrayList<ResourcesLibrary> loan) {
        this.loan = loan;
    }

    @Override
    public String toString(){
        return "\nNombre: " + name + "\nContraseña: " + password;
    }

    @Override
    public boolean authenticate(String name, String password) {
        if(this.name.equals(name) && this.password.equals(password))
            return true;
        else
            return false;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
