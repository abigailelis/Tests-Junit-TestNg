package tdv.teclasunidos.entities;

public class DniInvalidoException extends Exception {

    public DniInvalidoException(String string){
        System.out.println("El dni no es válido: " + string);
    }

}
