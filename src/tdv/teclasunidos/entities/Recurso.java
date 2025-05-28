package tdv.teclasunidos.entities;

public class Recurso {
    private String nombre;
    private String ubicacion;


	public Recurso(String nombre, String ubicacion) throws NombreRecursoNoPermitido{
		if ("Oficina".equals(nombre))
			throw new NombreRecursoNoPermitido("El recurso no puede llamarse 'Oficina'");

		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}


	public String getNombre() {
		return nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}

    @Override
	public String toString(){
		return "Nombre: " + nombre + ", ubicación: " + ubicacion + " ;";
	}
}

