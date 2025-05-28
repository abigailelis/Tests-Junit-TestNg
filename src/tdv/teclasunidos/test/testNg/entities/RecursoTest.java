package tdv.teclasunidos.test.testNg.entities;

import org.testng.Assert;
import org.testng.annotations.*;
import tdv.teclasunidos.entities.NombreRecursoNoPermitido;
import tdv.teclasunidos.entities.Recurso;
import tdv.teclasunidos.test.testNg.resources.DataProvidersCsv.DataProviderRecursoCSV;

public class RecursoTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("RecursoTest : beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("RecursoTest : afterSuite");
    }

    @BeforeTest
    public void beforeTests() {
        System.out.println("RecursoTest : beforeTests");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("RecursoTest : afterTests");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("RecursoTest : beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("RecursoTest : afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("RecursoTest : beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("RecursoTest : afterMethod");
    }

    @Test  (dataProvider = "generadorElementoRandom",
            dataProviderClass = DataProviderRecursoCSV.class,
            description = "Test para verificar restricción del nombre Oficina como recurso",
            invocationCount = 5)

    public void TestVerificarNombreNoPermitido( String nombre, String ubicacion ) {
        Recurso recurso = null;
        try {
            recurso = new Recurso( nombre, ubicacion );
        } catch (NombreRecursoNoPermitido e) {
            throw new RuntimeException(e);
        }

        if(nombre.equals("Oficina"))
            Assert.assertNull(recurso, "El recurso con el nombre Oficina no está permitido");
        else
            Assert.assertNotNull(recurso, "Falla al agregar el recurso");

    }
}
