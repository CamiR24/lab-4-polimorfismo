package com.biblioteca.controller;

import com.biblioteca.bean.Book;
import com.biblioteca.bean.Loan;
import com.biblioteca.bean.Magazine;
import com.biblioteca.bean.PremiumUser;
import com.biblioteca.bean.ResourcesLibrary;
import com.biblioteca.inter.ProfileInterface;
import com.biblioteca.view.ConsoleUI;

import java.util.*;

public class PremiumController {
    private PremiumUser currentUser;
    private CSVController csvController;
    private ConsoleUI consoleUI;
    private ArrayList<ProfileInterface> usuarios;

    public PremiumController(ConsoleUI consoleUI, ArrayList<ProfileInterface> usuarios) {
        this.consoleUI = consoleUI;
        this.usuarios = usuarios;
        csvController = new CSVController();
    }

    public void registerUser() {
        String name = consoleUI.getStringInput("Registre un nombre de usuario: ");
        String password = consoleUI.getStringInput("Registre una contraseña: ");
        String ID = Integer.toString(usuarios.size());

        currentUser = new PremiumUser(name, password, ID);
        usuarios.add(currentUser);
        csvController.guardarUsuarios(usuarios);
        System.out.println(currentUser);

        consoleUI.showMessage("Usuario registrado exitosamente.");
    }

    public void modoSeleccion(ArrayList<ResourcesLibrary> archivos){
        ArrayList<ResourcesLibrary> userFiles = currentUser.getLoan();
        boolean regresar = false;

        while(!regresar){

            consoleUI.showMessage("\nArchivos seleccionados: ");

            for(ResourcesLibrary archivo : userFiles){
                System.out.println(archivo);
            }

            String[] opciones = {"\nModo seleccion","1. Agregar un libro (5 maximo)", "2. Agregar revista", "3. Vaciar lista", "4. Regresar"};
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
                            if(currentUser.getBooks() < 5){
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
        String horario = null;
        String direccion = null; 

        diasEntrega = consoleUI.getIntInput("Ingrese el plazo de dias hasta la entrega (50 Max.): ");
        if(diasEntrega > 50){
            consoleUI.showMessage("No puede ingresar un plazo de mas de 50 dias");
            return;
        }
        direccion = consoleUI.getStringInput("Ingrese la direccion de envio: ");
        horario = consoleUI.getStringInput("Ingrese el horario de entrega. Escriba AM o PM: ");
        switch (horario) {
            case "AM":
                
                break;
            case "PM":

                break;
            default:
                consoleUI.showMessage("\nSolo puede ingresar un valor de AM o PM");
                return;
        }

        Loan loan = new Loan(diasEntrega, horario, "Indefinida", direccion, "No aplica");
        System.out.println(loan);
        loans.add(loan);
        csvController.guardarPrestamos(loans);
    }

    public void loginUser(PremiumUser user){
        this.currentUser = user; 
    }

    public PremiumUser getCurrentUser(){
        return currentUser;
    }
}
