package testNg.repositories;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import tdv.teclasunidos.entities.DniInvalidoException;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.repositories.SocioRepository;
import tdv.teclasunidos.test.testNg.resources.DataProvidersCsv.DataProviderSociosCSV;

public class SocioRepositoryTest {

    static SocioRepository socioRepository = new SocioRepository();

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("SocioRepository : beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("SocioRepository : afterSuite");
    }

    @BeforeTest
    public void beforeTests() {
        System.out.println("SocioRepository : beforeTests");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("SocioRepository : afterTests");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("SocioRepository : beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("SocioRepository : afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("SocioRepository : beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("SocioRepository : afterMethod");
    }

    @Test  (dataProvider = "generadorDatos" ,
            dataProviderClass = DataProviderSociosCSV.class,
            description = "Test del método agregar socio")

    public void TestAgregar(String nombre, int edad, String direccion, String dni) {
        Socio socio = agregarSocio( nombre, edad, direccion, dni );
        Socio socioActual = socioRepository.buscarPorDni( dni );

        //Espero el mismo objeto
        Assert.assertSame(socioActual, socio , "El socio no se agregó correctamente.");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderSociosCSV.class,
            description = "Test del método eliminar socio")

    public void TestEliminar(String nombre, int edad, String direccion, String dni) {
        Socio socioAgregado = agregarSocio( nombre, edad, direccion, dni );
        Assert.assertNotNull(socioAgregado, "El socio no se agregó correctamente"); //verifica que se agregó correctamente

        socioRepository.eliminar( dni );
        Socio socioActual = socioRepository.buscarPorDni( dni );

        Assert.assertNull(socioActual, "El socio no se eliminó correctamente.");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderSociosCSV.class,
            description = "Test del método actualizar socio")

    public void TestActualizar(String nombre, int edad, String direccion, String dni) {
        Socio socio = agregarSocio(nombre, edad, direccion, dni);

        try {
            socio.setNombre( "Paulo Sanchez" );
        } catch (NombreMuyLargoException e) {
            throw new RuntimeException(e);
        }

        socioRepository.actualizar( socio );
        Socio socioActual = socioRepository.buscarPorDni( dni );

        Assert.assertSame(socioActual, socio, "El socio no se editó correctamente.");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderSociosCSV.class,
            description = "Test para verificar excepción nombre muy largo",
            expectedExceptions = NombreMuyLargoException.class)

    public void TestNombreMuyLargo(String nombre, int edad, String direccion, String dni) {
        String nombreActual = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEF";

        //Verifica que realmente se largue la exception del constructor de Socio
        agregarSocio(nombreActual, edad, direccion, dni);
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderSociosCSV.class,
            description = "Test para verificar excepción dni inválido",
            expectedExceptions = DniInvalidoException.class)

    public void TestDniInvalido(String nombre, int edad, String direccion, String dni) {
        String dniConPuntos = "12.345.678";
        String dniCorto = "12345";
        String dniLargo = "12345678";
        String dniValido = "123456";

        //Verifica que realmente se largue la excepción DniInvalidoException del constructor de Socio
        Assert.assertThrows ( DniInvalidoException.class, () -> agregarSocio(nombre, edad, direccion, dniConPuntos) );
        Assert.assertThrows ( DniInvalidoException.class, () -> agregarSocio(nombre, edad, direccion, dniCorto) );
        Assert.assertThrows ( DniInvalidoException.class, () -> agregarSocio(nombre, edad, direccion, dniLargo) );
        Assert.assertThrows ( DniInvalidoException.class, () -> agregarSocio(nombre, edad, direccion, dniValido) );
        Assert.assertThrows ( DniInvalidoException.class, () -> agregarSocio(nombre, edad, direccion, dni) );//Junit
        //agregarSocio(nombre, edad, direccion, dniLargo);  //testNg
    }

    //Función auxiliar para agregar un socio
    public Socio agregarSocio(String nombre, int edad, String direccion, String dni) {
        Socio socio = null;

        try {
            socio = new Socio( nombre, edad, direccion, dni );
        } catch (NombreMuyLargoException | EdadInvalidaException | DniInvalidoException e) {
            throw new RuntimeException(e);
        }

        socioRepository.agregar( socio );
        System.out.println( "Socio agregado: " + socioRepository.buscarPorDni( dni ) );

        return socio;
    }

}
