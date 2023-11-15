package com.biblioteca.controller;

import com.biblioteca.bean.BaseUser;
import com.biblioteca.bean.Book;
import com.biblioteca.bean.Loan;
import com.biblioteca.bean.Magazine;
import com.biblioteca.bean.ResourcesLibrary;
import com.biblioteca.inter.ProfileInterface;
import com.biblioteca.view.ConsoleUI;

import java.util.*;

public class BaseUserController {
    private BaseUser currentUser;
    private CSVController csvController;
    private ConsoleUI consoleUI;
    private ArrayList<ProfileInterface> usuarios;

    public BaseUserController(ConsoleUI consoleUI, ArrayList<ProfileInterface> usuarios) {
        this.consoleUI = consoleUI;
        this.usuarios = usuarios;
        csvController = new CSVController();
    }

    public void registerUser() {
        String name = consoleUI.getStringInput("Registre un nombre de usuario: ");
        String password = consoleUI.getStringInput("Registre una contraseña: ");
        String ID = Integer.toString(usuarios.size());

        currentUser = new BaseUser(name, password, ID);
        usuarios.add(currentUser);
        csvController.guardarUsuarios(usuarios);
        System.out.println(currentUser);

        consoleUI.showMessage("Usuario registrado exitosamente.");
    }

    public void loginUser(BaseUser user){
        this.currentUser = user; 
    }

    public void modoSeleccion(ArrayList<ResourcesLibrary> archivos){
        ArrayList<ResourcesLibrary> userFiles = currentUser.getLoan();
        boolean regresar = false;

        while(!regresar){

            consoleUI.showMessage("\nArchivos seleccionados: ");

            for(ResourcesLibrary archivo : userFiles){
                System.out.println(archivo);
            }

            String[] opciones = {"\nModo seleccion","1. Agregar un libro (3 maximo)", "2. Agregar revista", "3. Vaciar lista", "4. Regresar"};
            String opcion = consoleUI.menuIngreso(opciones);

            switch(opcion){
                case "1":
                    consoleUI.showMessage("Libros disponibles: ");
                    for(ResourcesLibrary archivo : archivos){
                        if(archivo instanceof Book)
                            System.out.println(archivo);
                    }
                    String name = consoleUI.getStringInput("Ingrese el nombre del libro que desea: ");
                    for(ResourcesLibrary archivo : archivos){
                        if(archivo.getName().equals(name)){
                            if(currentUser.getBooks() < 3){
                                userFiles.add(archivo);
                                consoleUI.showMessage("\nLibro seleccionado correctamente");
                                currentUser.setBooks(currentUser.getBooks() + 1);
                            }else
                                consoleUI.showMessage("Se ha alcanzado el limite de libros que se pueden prestar");
                            regresar = true;
                        }
                    }
                    break;
                case "2":
                    consoleUI.showMessage("Revistas disponibles: ");
                    for(ResourcesLibrary archivo : archivos){
                        if(archivo instanceof Magazine)
                            System.out.println(archivo);
                    }
                    name = consoleUI.getStringInput("\nIngrese el nombre de la revista que desea: ");
                    for(ResourcesLibrary archivo : archivos){
                        if(archivo.getName().equals(name)){
                            userFiles.add(archivo);
                            consoleUI.showMessage("\nRevista seleccionado correctamente");
                            regresar = true;
                        }
                    }                  
                    break;
                case "3":
                    userFiles.removeAll(userFiles);
                    consoleUI.showMessage("\nSe ha vaciado la lista.");
                    break;
                case "4":
                    regresar = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico solamente");
            } 
        }
    }

    public void modoPrestamo(ArrayList<Loan> loans){
        
        int diasEntrega; 
        String sucursal = null;
        String pasarHoras = null; 

        diasEntrega = consoleUI.getIntInput("Ingrese el plazo de dias hasta la entrega (30 Max.): ");
        if(diasEntrega > 30){
            consoleUI.showMessage("No puede ingresar un plazo de mas de 30 dias");
            return;
        }
        sucursal = consoleUI.getStringInput("Ingrese la sucursal para recoger el prestamo: ");
        pasarHoras = consoleUI.getStringInput("Ingrese si pasara a las 12 o 24 horas de haber realizado la solicitud. Escriba 12 o 24: ");
        switch (pasarHoras) {
            case "12":
                
                break;
            case "24":

                break;
            default:
                consoleUI.showMessage("\nSolo puede ingresar un valor de 12 o 24");
                return;
        }

        Loan loan = new Loan(diasEntrega, "AM", sucursal, "Indefinido", pasarHoras);
        System.out.println(loan);
        loans.add(loan);
        csvController.guardarPrestamos(loans);
    }

    public BaseUser getCurrentUser(){
        return currentUser;
    }
}
