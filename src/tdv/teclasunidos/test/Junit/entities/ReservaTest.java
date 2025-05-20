package tdv.teclasunidos.test.entities;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.Recurso;
import tdv.teclasunidos.entities.Reserva;
import tdv.teclasunidos.entities.Socio;
import java.time.LocalDateTime;

public class ReservaTest {

    static Reserva reserva;
    static Recurso recursoOcupado;
    static Socio socio;
    static LocalDateTime fechaHoraInicio;
    static LocalDateTime fechaHoraFin;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("ReservaTest BeforeClass: setUpBeforeClass");

        recursoOcupado = new Recurso("Pileta", "Necochea");
        socio = new Socio("Pepe", 28, "Calle 25", "12.345.789");
        fechaHoraInicio = LocalDateTime.of(2025, 5, 19, 16, 30);
        fechaHoraFin = LocalDateTime.of(2025, 5, 19, 20, 30);
        reserva = new Reserva(recursoOcupado, socio, fechaHoraInicio, fechaHoraFin);
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
        fechaHoraInicio = LocalDateTime.of(2025, 5, 19, 20, 30);
        Reserva reservaNueva = new Reserva(recursoOcupado, socio, fechaHoraInicio, fechaHoraFin);

        Assert.assertFalse("Los recursos se superponen", reserva.seSuperpone(reservaNueva.getInicio(), reservaNueva.getFin()));
    }
}
