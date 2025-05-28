package tdv.teclasunidos.entities;

public class Socio {
    private String nombre;
    private int edad;
    private String direccion;
    private String dni;

    public Socio(String nombre, int edad, String direccion, String dni) throws NombreMuyLargoException, EdadInvalidaException, DniInvalidoException {

		if (nombre.length()>50)
    		throw new NombreMuyLargoException();
        this.nombre = nombre;

        if (edad <0 ||edad >100) 
        	throw new EdadInvalidaException();
        this.edad = edad;

		//Crear la exception
		if(dni.contains(".") || dni.length() < 6 || dni.length() > 7)
			throw new DniInvalidoException("El dni no puede contener puntos y debe tener entre 6 y 7 números");
		this.dni = dni;

        this.direccion = direccion;
        

    }

	public int getEdad() {
		return this.edad;
	}

	public String getDni() {
		return this.dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDireccion(){
		return this.direccion;
	}

	public void setNombre(String nombre) throws NombreMuyLargoException {
		if (nombre.length()>50)
			throw new NombreMuyLargoException();
		this.nombre = nombre;
	}

	@Override
	public String toString(){
		return "Nombre: " + nombre + ", " +
				" Edad: " + edad + ", " +
				" Dirección: " + direccion + ", " +
				" DNI: " + dni + "; ";
	}

    // setters
}

