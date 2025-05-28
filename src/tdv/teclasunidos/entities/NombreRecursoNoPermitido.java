package tdv.teclasunidos.entities;

public class NombreRecursoNoPermitido extends Exception {

    public NombreRecursoNoPermitido(String message) {
        System.out.println("El dni no es válido: " + message);
    }
}
