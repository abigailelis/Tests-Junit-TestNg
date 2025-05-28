package tdv.teclasunidos.test.testNg.resources.DataProvidersCsv;

import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class DataProviderCSV {

    private final String csvFile;

    public DataProviderCSV( String csvFile){
        this.csvFile = csvFile;
    }

    protected List<String[]> leerDatosDesdeCSV() throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Saltar encabezados

            while ((line = br.readLine()) != null) {
                datos.add(line.split(",")); // Retorna los datos sin modificar
            }
        }
        return datos;
    }

    @DataProvider(name = "generadorDatos")
    public Iterator<Object[]> getSocios() throws IOException {
        List<Object[]> datosProcesados = parsearDatosCsv();

        return datosProcesados.iterator();
    }

    @DataProvider(name = "generadorElementoRandom")
    public Object[][] generarElementoRandom() throws IOException {
        List<Object[]> datosProcesados = parsearDatosCsv();

        Collections.shuffle(datosProcesados);
        return new Object[][]{datosProcesados.getFirst()}; // Retorna un socio aleatorio
    }

    protected abstract List<Object[]> parsearDatosCsv() throws IOException;



}


