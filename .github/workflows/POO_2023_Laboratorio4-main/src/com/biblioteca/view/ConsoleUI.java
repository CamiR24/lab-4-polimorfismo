package com.biblioteca.view;

import java.util.*;

public class ConsoleUI {    
    Scanner scan;

    //BaseUserController baseUserController = new BaseUserController();

    public ConsoleUI(){
        scan = new Scanner(System.in);
        System.out.println("\nBienvenido al programa de la biblioteca");
    }

    public String menuIngreso(String[] opciones){
        String opcion = ""; 

        for (int i = 0; i < opciones.length; i++) {
            System.out.println(opciones[i]);
        }

        opcion = scan.nextLine();  

        return opcion;
    }

    public String getStringInput(String message) {
        String input = null; 
        System.out.print(message);
        input = scan.nextLine();
        return input;
    }

    public int getIntInput(String message) {
        int input = 0;
        boolean error = false;

        do{
            System.out.print(message);
            try{
                input = Integer.parseInt(scan.nextLine());//Se almacena el dato del ID
                error = false;
            }catch(Exception e){
                System.out.println("\nIntroduzca un numero valido");
                error = true;
            }
        } while(error);

        return input;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
