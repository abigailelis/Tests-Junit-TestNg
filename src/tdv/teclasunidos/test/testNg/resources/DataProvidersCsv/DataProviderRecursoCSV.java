package tdv.teclasunidos.test.testNg.resources.DataProvidersCsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderRecursoCSV extends DataProviderCSV{

    public DataProviderRecursoCSV(){
        super("src/tdv/teclasunidos/test/testNg/resources/csv/recurso.csv");
    }

    @Override
    public List<Object[]> parsearDatosCsv() throws IOException {
        List<String[]> datosCrudos = leerDatosDesdeCSV();
        List<Object[]> datosProcesados = new ArrayList<>();

        for (String[] data : datosCrudos) {
            datosProcesados.add(new Object[]{data[0],data[1]});
        }

        return datosProcesados;
    }
}
