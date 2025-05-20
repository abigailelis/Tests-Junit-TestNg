package tdv.teclasunidos.test.entities;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.Actividad;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.entities.Socio;

public class ActividadTest {

    static Actividad actividad;
    static Actividad boxeo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("ActividadTest BeforeClass : setUpBeforeClass");
        actividad = new Actividad("Natación", "Pedro", "Lunes", 6, "Pileta 1", 18);
        boxeo = new Actividad ("Boxeo", "Juan", "Martes", 16, "Ring", 1);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("ActividadTest AfterClass : tearDownAfterClass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("ActividadTest Before : setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ActividadTest After : tearDown");
    }

    @Test
    @DisplayName("Test toString")
    public void TestToString(){
        Object actividadString = actividad.toString();
        Assert.assertTrue(actividadString instanceof String);
    }

    @Test
    @DisplayName("Test inscribir menores de 16 años a Boxeo")
    public void inscribirMenorEdadBoxeo() throws EdadInvalidaException, NombreMuyLargoException {
        Socio socio = new Socio("Pedrito", 16, "calle 30", "40.568.957");
        Assert.assertTrue("El socio no se agregó correctamente", boxeo.agregarInscripcion(socio));
    }

    @Test
    @DisplayName("Test superar cupos de actividad")
    public void inscribirConCupoSuperado() throws EdadInvalidaException, NombreMuyLargoException {
        Socio socio1 = new Socio("Pedrito", 16, "calle 30", "40.568.957");
        Socio socio2 = new Socio("Juanito", 18, "calle 11", "38.777.144");

        boxeo.agregarInscripcion(socio1);
        Assert.assertTrue("El socio no se agregó correctamente", boxeo.agregarInscripcion(socio2));
    }

}
