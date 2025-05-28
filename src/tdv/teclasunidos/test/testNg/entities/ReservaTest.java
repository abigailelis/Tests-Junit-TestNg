package tdv.teclasunidos.test.testNg.entities;

import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.*;
import tdv.teclasunidos.entities.*;
import tdv.teclasunidos.services.ReservaService;
import tdv.teclasunidos.test.testNg.resources.DataProvidersCsv.DataProviderReservaCSV;

import java.time.LocalDateTime;

public class ReservaTest {

    static ReservaService reservaService;
    static Recurso recurso;
    static Socio socioOriginal;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("ReservaTest : beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("ReservaTest : afterSuite");
    }

    @BeforeTest
    public void beforeTests() {
        System.out.println("ReservaTest : beforeTests");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("ReservaTest : afterTests");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("ReservaTest : beforeClass");

        reservaService = new ReservaService();
        try {
            recurso = new Recurso("Sala de Reuniones","Edificio Principal - Piso 1");
        } catch (NombreRecursoNoPermitido e) {
            throw new RuntimeException(e);
        }

        try {
            socioOriginal = new Socio("Juan", 28, "Calle 50", "124589");
        } catch (NombreMuyLargoException | EdadInvalidaException | DniInvalidoException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        System.out.println("ReservaTest : afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("ReservaTest : beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("ReservaTest : afterMethod");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderReservaCSV.class,
            description = "Test para verificar que no se puede reservar un recurso ocupado")

    public void TestReservarRecursoOcupado(LocalDateTime inicio, LocalDateTime fin){
        reservaService.reservar(recurso, socioOriginal, inicio, fin);

        //Ejemplo de dos horarios uno en el que puedo reservar y uno que se superpone
        boolean puedoReservarFalse = reservaService.reservar(recurso, socioOriginal, inicio, fin.plusHours(2));
        boolean puedoReservarTrue = reservaService.reservar(recurso, socioOriginal, fin.plusMinutes(30), fin.plusHours(2));

        Assert.assertTrue(puedoReservarTrue, "No se pudo reservar por otros motivos");
        Assert.assertFalse(puedoReservarFalse, "No se puede reservar porque los recursos se superponen");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderReservaCSV.class,
            description = "Test para verificar que no se puede borrar la reserva por otro socio")

    public void eliminarReservaPorOtroSocio(LocalDateTime inicio, LocalDateTime fin) {
        Socio socioDistinto = null;
        try {
            socioDistinto = new Socio("Pedro", 28, "Calle 25", "125589");
        } catch (NombreMuyLargoException | EdadInvalidaException | DniInvalidoException e) {
            throw new RuntimeException(e);
        }

        reservaService.reservar( recurso, socioOriginal, inicio, fin );

        boolean valorActual = reservaService.cancelarReserva( recurso, socioDistinto, inicio, fin );
        boolean valorEsperado = socioDistinto == socioOriginal;

        Assert.assertEquals( valorActual, valorEsperado, "No se puede eliminar la reserva de otro socio" );
    }
}
