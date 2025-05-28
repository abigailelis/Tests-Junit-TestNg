package tdv.teclasunidos.test.testNg.entities;

import org.junit.Assert;
import org.testng.annotations.*;
import tdv.teclasunidos.entities.*;
import tdv.teclasunidos.test.testNg.resources.DataProvidersCsv.DataProviderActividadCSV;

public class ActividadTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("ActividadRepository : beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("ActividadRepository : afterSuite");
    }

    @BeforeTest
    public void beforeTests() {
        System.out.println("ActividadRepository : beforeTests");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("ActividadRepository : afterTests");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("ActividadRepository : beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("ActividadRepository : afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("ActividadRepository : beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("ActividadRepository : afterMethod");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderActividadCSV.class,
            description = "Test del metodo toString de la entidad Actividad")

    public void TestToString(String nombre, String encargado, String horario, int edadMinima, String lugar, int cupo){
        Actividad actividad = new Actividad(nombre, encargado, horario, edadMinima, lugar, cupo);

        String stringActual = actividad.toString();
        String stringEsperado = actividad.getNombre() + "-" + actividad.getLugar(); //modelo de referencia

        Assert.assertEquals("El método toString no es el esperado. ", stringEsperado, stringActual);
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderActividadCSV.class,
            description = "Test para verificar que no se puedan inscribir menores de edad mínima en la actividad")

    public void inscribirMenorEdadActividad(String nombre, String encargado, String horario, int edadMinima, String lugar, int cupo) throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        Actividad actividad = new Actividad(nombre, encargado, horario, edadMinima, lugar, cupo);

        Socio socio = crearSocioEdadAleatoria(1, 0, 100);
        //modelo de referencia
        boolean valorRecibido = actividad.agregarInscripcion(socio);
        boolean valorEsperado = socio.getEdad() > edadMinima;

        Assert.assertEquals("El socio no se agregó correctamente", valorRecibido, valorEsperado);
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderActividadCSV.class,
            description = "Test para verificar que no se supere el cupo de la actividad")

    public void inscribirConCupoSuperado(String nombre, String encargado, String horario, int edadMinima, String lugar, int cupo) {

        Actividad actividad = new Actividad( nombre, encargado, horario, edadMinima, lugar, cupo );
        int maximo = generarNumeroAleatorio( 1, cupo + 1 ); //valor aleatorio
        int efectivos = 0;

        for(int i = 0; i < maximo; i++){
            Socio socio = crearSocioEdadAleatoria(i, 0, 100);
            if(actividad.agregarInscripcion(socio)){
                efectivos += 1;
            }
        }

        Socio socio = crearSocioEdadAleatoria(maximo, edadMinima + 1, 100);
        //modelo de referencia
        boolean inscripcionRecibida = actividad.agregarInscripcion(socio);
        boolean inscripcionEsperada = cupo > efectivos + 1;

        Assert.assertEquals("Se superó el limite de cupos de la actividad",inscripcionRecibida, inscripcionEsperada);
    }

    public int generarNumeroAleatorio(int min, int max){
            return (int) (Math.random() * (max - min + 1)) + min;
    }

    public Socio crearSocioEdadAleatoria(int numero, int min, int max){
        int edadAleatoria = generarNumeroAleatorio ( min, max );
        Socio socio = null;
        try {
            socio = new Socio("Socio " + numero, edadAleatoria, "calle 30", "4056897");
        } catch (NombreMuyLargoException | EdadInvalidaException | DniInvalidoException e) {
            throw new RuntimeException(e);
        }

        return socio;
    }

}
