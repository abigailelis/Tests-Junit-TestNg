package tdv.teclasunidos.test.Junit.entities;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.*;
import tdv.teclasunidos.services.ReservaService;
import java.time.LocalDateTime;

public class ReservaTest {

    static ReservaService reservaService;
    static Recurso recurso;
    static Socio socio;
    static LocalDateTime fechaHoraInicio;
    static LocalDateTime fechaHoraFin;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("ReservaTest BeforeClass: setUpBeforeClass");

        reservaService = new ReservaService();
        recurso = new Recurso("Pileta", "Necochea");
        socio = new Socio("Pepe", 28, "Calle 25", "123457");
        fechaHoraInicio = LocalDateTime.of(2025, 5, 19, 16, 30);
        fechaHoraFin = LocalDateTime.of(2025, 5, 19, 20, 30);

        reservaService.reservar(recurso, socio, fechaHoraInicio, fechaHoraFin);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        System.out.println("ReservaTest AfterClass: setUpBeforeClass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("ReservaTest Before : setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ReservaTest After : tearDown");
    }

    @Test
    @DisplayName("Test reservar recurso ocupado")
    public void TestReservarRecursoOcupado(){

        fechaHoraInicio = LocalDateTime.of(2025, 5, 19, 19, 30);
        boolean puedoReservar = reservaService.reservar(recurso, socio, fechaHoraInicio, fechaHoraFin);

        Assert.assertTrue("No se puede reservar porque los recursos se superponen", puedoReservar);
    }

    @Test
    @DisplayName("Test eliminar una reserva por otro usuario")
    public void eliminarReservaPorOtroSocio() throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        Socio socioBorrar = new Socio("Pedro", 28, "Calle 25", "125589");
        boolean puedoCancelar = reservaService.cancelarReserva(recurso, socioBorrar, fechaHoraInicio, fechaHoraFin);

        Assert.assertTrue("No se puede eliminar la reserva de otro socio", puedoCancelar);

    }
}
