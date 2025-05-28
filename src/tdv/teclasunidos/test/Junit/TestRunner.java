package tdv.teclasunidos.test.Junit;

import org.junit.runner.JUnitCore;

public class TestRunner {

    public static void main (String [] args){
        System.out.println("TestRunner -> Comienzo del runner");
        JUnitCore.runClasses(TestSuite.class);
        System.out.println("TestRunner -> Fin del runner");
    }

}
