package tdv.teclasunidos.test.testNg.resources.DataProvidersCsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderSociosCSV extends DataProviderCSV {

    public DataProviderSociosCSV(){
        super("src/tdv/teclasunidos/test/testNg/resources/csv/socios.csv");
    }

    @Override
    public List<Object[]> parsearDatosCsv() throws IOException {
        List<String[]> datosCrudos = leerDatosDesdeCSV();
        List<Object[]> datosProcesados = new ArrayList<>();

        for (String[] data : datosCrudos) {
            datosProcesados.add(new Object[]{data[0], Integer.parseInt(data[1]), data[2], data[3]});
        }

        return datosProcesados;
    }

}
