package com.biblioteca.bean;

import java.util.ArrayList;

public class BaseUser extends User {

    public BaseUser(String name, String password, String ID) {
        super(name, password, ID);
    }

    public void modoSeleccion(ArrayList<ResourcesLibrary> archivos) {
    }
}
