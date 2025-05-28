package tdv.teclasunidos.test.testNg.resources.DataProvidersCsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderActividadCSV extends DataProviderCSV{

    public DataProviderActividadCSV(){
        super("src/tdv/teclasunidos/test/testNg/resources/csv/actividad.csv");
    }

    @Override
    public List<Object[]> parsearDatosCsv() throws IOException {
        List<String[]> datosCrudos = leerDatosDesdeCSV();
        List<Object[]> datosProcesados = new ArrayList<>();

        for (String[] data : datosCrudos) {
            datosProcesados.add(new Object[]{data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])});
        }

        return datosProcesados;
    }
}
