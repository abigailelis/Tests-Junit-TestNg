package tdv.teclasunidos.test.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tdv.teclasunidos.test.Junit.entities.ActividadTest;
import tdv.teclasunidos.test.Junit.entities.RecursoTest;
import tdv.teclasunidos.test.Junit.entities.ReservaTest;
import tdv.teclasunidos.test.Junit.repositories.SocioRepositoryTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        SocioRepositoryTest.class,
        ActividadTest.class,
        RecursoTest.class,
        ReservaTest.class,
})
public class TestSuite {
    //Clase vacia
}
