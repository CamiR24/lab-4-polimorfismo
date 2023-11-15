package com.biblioteca.inter;

public interface ProfileInterface {

    void changePassword(String newPassword);

    public boolean authenticate(String name, String password); 
}
