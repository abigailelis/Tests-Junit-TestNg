package tdv.teclasunidos.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tdv.teclasunidos.test.entities.ActividadTest;
import tdv.teclasunidos.test.repositories.SocioRepositoryTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        SocioRepositoryTest.class,
        ActividadTest.class,
})
public class TestSuite {
    //Clase vacia
}
