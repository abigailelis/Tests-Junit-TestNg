package tdv.teclasunidos.entities;

public class NombreMuyLargoException extends Exception {

    public NombreMuyLargoException(){
        System.out.println("El nombre excedió el limite máximo de caracteres");
    }
}
