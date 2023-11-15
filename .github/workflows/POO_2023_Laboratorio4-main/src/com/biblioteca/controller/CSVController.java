package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import com.biblioteca.bean.BaseUser;
import com.biblioteca.bean.Loan;
import com.biblioteca.bean.PremiumUser;
import com.biblioteca.inter.ProfileInterface;

public class CSVController{
    public ArrayList<Loan> leerPrestamos(String path){
        ArrayList<Loan> loans = new ArrayList<Loan>();

        try{
            Scanner scan = new Scanner(new File(path));
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }

            while(scan.hasNextLine()){
                String[] loansData = scan.nextLine().split(";");

                Loan loan = new Loan(Integer.parseInt(loansData[0]), loansData[1], loansData[2], loansData[3], loansData[4]);
                loans.add(loan);
            }

        System.out.println("Archivo de prestamos cargados correctamente...");

        }catch(Exception e){
            System.out.println("\nNo se ha podido cargar el archivo " + e);
        }

        return loans;
    }
    
    public ArrayList<ProfileInterface> leerUsuarios(String path){
        ArrayList<ProfileInterface> usuarios = new ArrayList<ProfileInterface>();

        try{
            Scanner scan = new Scanner(new File(path));
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }

            while(scan.hasNextLine()){
                String[] usersData = scan.nextLine().split(";");

                switch (usersData[3]) {
                    case "Base":
                        BaseUser baseUser = new BaseUser(usersData[0], usersData[1], usersData[2]);
                        usuarios.add(baseUser);
                        break;
                    case "Premium":
                        PremiumUser premiumUser = new PremiumUser(usersData[0], usersData[1], usersData[2]);
                        usuarios.add(premiumUser);
                }
            }

        System.out.println("Archivo de usuarios cargados correctamente...");

        }catch(Exception e){
            System.out.println("\nNo se ha podido cargar el archivo " + e);
        }

        return usuarios;
    }

    public void guardarUsuarios(ArrayList<ProfileInterface> usuarios){
        File file = new File("usuarios.csv");

        try{
            PrintWriter out = new PrintWriter(file);

            String[] titulos = {"Nombre", "Contraseña", "ID","Plan"};

            for(String titulo : titulos)
                out.print(titulo + ";");//Escribimos los titulos

            out.println();

            for(ProfileInterface usuario : usuarios){
                if(usuario instanceof BaseUser)
                    out.println(((BaseUser)usuario).getName() + ";" + ((BaseUser)usuario).getPassword() + ";" + ((BaseUser)usuario).getID() + ";Base");
                else
                    out.println(((PremiumUser)usuario).getName() + ";" + ((PremiumUser)usuario).getPassword() + ";" + ((PremiumUser)usuario).getID() + ";Premium");
            }

            out.close();

            System.out.println("\nArchivo csv guardado");
        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public void guardarPrestamos(ArrayList<Loan> loans){
        File file = new File("prestamos.csv");

        try{
            PrintWriter out = new PrintWriter(file);

            String[] titulos = {"Dias de entrega", "Horario de entrega", "Sucursal","Direccion","Pasara por el prestamo a las"};

            for(String titulo : titulos)
                out.print(titulo + ";");//Escribimos los titulos

            out.println();

            for(Loan loan : loans){
               out.println(loan.getDiasEntrega() + ";" + loan.getHorario() + ";" + loan.getSucursal() + ";" + loan.getDireccion() + ";" + loan.getPasarHoras());
            }

            out.close();

            System.out.println("\nArchivo csv guardado");
        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el archivo");
        }
    }
}
