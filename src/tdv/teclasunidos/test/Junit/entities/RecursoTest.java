package tdv.teclasunidos.test.Junit.entities;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.NombreRecursoNoPermitido;
import tdv.teclasunidos.entities.Recurso;

public class RecursoTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("RecursoTest BeforeClass: setUpBeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        System.out.println("RecursoTest AfterClass: setUpBeforeClass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("RecursoTest Before : setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("RecursoTest After : tearDown");
    }

    @Test
    @DisplayName("Test recurso Oficina")
    public void TestVerificarNombreNoPermitido(){
        Recurso recurso = null;
        try {
            recurso = new Recurso("Oficina", "Necochea");
        } catch (NombreRecursoNoPermitido e) {
            throw new RuntimeException(e);
        }

        Assert.assertNotNull("El recurso con el nombre Oficina no se puede crear", recurso);
    }
}
