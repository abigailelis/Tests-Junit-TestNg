package tdv.teclasunidos.test.testNg.resources.csv;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataProviderCSV {


    private static List<Object[]> leerSociosDesdeCSV() throws IOException {
        List<Object[]> socios = new ArrayList<>();
        String csvFile = "src/tdv/teclasunidos/test/testNg/resources/csv/socios.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Saltar la primera línea (encabezados)

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                socios.add(new Object[]{data[0], Integer.parseInt(data[1]), data[2], data[3]});
            }
        }
        return socios;
    }

    @DataProvider(name = "generadorSocios")
    public static Iterator<Object[]> readCSV() throws IOException {
        return leerSociosDesdeCSV().iterator();
    }

    @DataProvider(name = "generadorSocioRandom")
    public Object[][] generarSocioRandom() throws IOException {
        List<Object[]> socios = leerSociosDesdeCSV();
        Collections.shuffle(socios);
        return new Object[][]{socios.getFirst()}; // Retorna solo un socio aleatorio
    }
}


