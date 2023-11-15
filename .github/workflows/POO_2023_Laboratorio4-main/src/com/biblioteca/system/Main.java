package com.biblioteca.system;

import java.util.*;

import com.biblioteca.bean.*;
import com.biblioteca.controller.*;
import com.biblioteca.inter.ProfileInterface;
import com.biblioteca.view.ConsoleUI;;

public class Main {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        CSVController csvController = new CSVController();

        ArrayList<ProfileInterface> usuarios;
        usuarios = csvController.leerUsuarios("usuarios.csv");
        ArrayList<ResourcesLibrary> archivos = new ArrayList<ResourcesLibrary>();
        ArrayList<Loan> loans;
        loans = csvController.leerPrestamos("prestamos.csv");

        Book book1 = new Book("The Catcher in the Rye", "978-0-316-76948-0", "Fiction", 25.99);
        Book book2 = new Book("To Kill a Mockingbird", "978-0-06-112008-4", "Fiction", 19.99);
        Book book3 = new Book("1984", "978-0-452-28423-4", "Science Fiction", 29.99);
        Book book4 = new Book("The Great Gatsby", "978-0-7432-7356-5", "Fiction", 22.50);
        Book book5 = new Book("The Hobbit", "978-0-261-10295-2", "Fantasy", 27.99);

        Magazine magazine1 = new Magazine("National Geographic", "978-1-4262-0671-0", "Science", 8.99);
        Magazine magazine2 = new Magazine("Time", "978-1-60320-868-1", "News", 5.99);
        Magazine magazine3 = new Magazine("Fashion Weekly", "978-0-306-40615-7", "Fashion", 3.99);
        Magazine magazine4 = new Magazine("Sports Illustrated", "978-1-55800-501-2", "Sports", 7.99);
        Magazine magazine5 = new Magazine("Cooking Magazine", "978-0-9828783-1-2", "Cooking", 4.99);

        archivos.add(book1);
        archivos.add(book2);
        archivos.add(book3);
        archivos.add(book4);
        archivos.add(book5);

        archivos.add(magazine1);
        archivos.add(magazine2);
        archivos.add(magazine3);
        archivos.add(magazine4);
        archivos.add(magazine5);

        BaseUserController baseUserController = new BaseUserController(consoleUI, usuarios);
        PremiumController premiumController = new PremiumController(consoleUI, usuarios);

        boolean salir = false;
        boolean ingreso = false;

        while(!salir && !ingreso){

            String[] opciones = {"\nINGRESO","¿Que desea hacer?", "1. Registrarse", "2. Iniciar Sesion","3. Salir"};
            String opcion = consoleUI.menuIngreso(opciones);

            switch(opcion){
                case "1":
                    ingreso = true;
                    menuRegistrar(consoleUI, usuarios, baseUserController, premiumController, archivos, loans);
                    break;
                case "2":
                    menuIngreso(consoleUI, usuarios, baseUserController, premiumController, archivos, loans);
                    ingreso = true;
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico solamente");
            } 
        }
    }

    public static void menuRegistrar(ConsoleUI consoleUI, ArrayList<ProfileInterface> usuarios, BaseUserController baseUserController, PremiumController premiumController, ArrayList<ResourcesLibrary> archivos, ArrayList<Loan> loans){
        boolean salir = false;
        boolean ingreso = false;

        while(!salir && !ingreso){

            String[] opciones = {"\nEscoge un plan","1. Base", "2. Premium", "3. Salir"};
            String opcion = consoleUI.menuIngreso(opciones);

            switch(opcion){
                case "1":
                    baseUserController.registerUser();
                    ingreso = true;
                    menuBaseUser(baseUserController, consoleUI, archivos, loans);
                    break;
                case "2":
                    premiumController.registerUser();
                    ingreso = true;
                    menuPremiumUser(premiumController, consoleUI, archivos, loans);
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico solamente");
            } 
        }
    }

    public static void menuIngreso(ConsoleUI consoleUI, ArrayList<ProfileInterface> usuarios, BaseUserController baseUserController, PremiumController premiumController, ArrayList<ResourcesLibrary> archivos, ArrayList<Loan> loans) {
        boolean authenticated = false;
    
        while (!authenticated) {
            String name = consoleUI.getStringInput("Ingrese su nombre de usuario: ");
            String password = consoleUI.getStringInput("Ingrese su contraseña: ");
    
            for (ProfileInterface usuario : usuarios) {
                if (usuario.authenticate(name, password)) {
                    authenticated = true;
                    if (usuario instanceof BaseUser) {
                        baseUserController.loginUser((BaseUser)usuario);
                        consoleUI.showMessage("Sesión iniciada exitosamente.");
                        menuBaseUser(baseUserController, consoleUI, archivos, loans);
                    } else if (usuario instanceof PremiumUser) {
                        premiumController.loginUser((PremiumUser)usuario);
                        consoleUI.showMessage("Sesión iniciada exitosamente.");
                        menuPremiumUser(premiumController, consoleUI, archivos, loans);
                    }
                    break;
                }
            }
    
            if (!authenticated) {
                consoleUI.showMessage("Usuario o contraseña incorrectos. Inténtelo de nuevo.");
            }
        }
    }

    public static void menuBaseUser(BaseUserController baseUserController, ConsoleUI consoleUI, ArrayList<ResourcesLibrary> archivos, ArrayList<Loan> loans){
        boolean salir = false;

        while(!salir){

            String[] opciones = {"\nOpciones de usuario","1. Modo seleccion", "2. Modo reserva", "3. Salir"};
            String opcion = consoleUI.menuIngreso(opciones);

            switch(opcion){
                case "1":
                    baseUserController.modoSeleccion(archivos);
                    break;
                case "2":
                    baseUserController.modoPrestamo(loans);
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico solamente");
            } 
        }
    }

    public static void menuPremiumUser(PremiumController premiumController, ConsoleUI consoleUI, ArrayList<ResourcesLibrary> archivos, ArrayList<Loan> loans){
        boolean salir = false;

        while(!salir){

            String[] opciones = {"\nOpciones de usuario","1. Modo seleccion", "2. Modo reserva", "3. Salir"};
            String opcion = consoleUI.menuIngreso(opciones);

            switch(opcion){
                case "1":
                    premiumController.modoSeleccion(archivos);
                    break;
                case "2":
                    premiumController.modoPrestamo(loans);
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Ingrese un valor numerico solamente");
            } 
        }
    }
}