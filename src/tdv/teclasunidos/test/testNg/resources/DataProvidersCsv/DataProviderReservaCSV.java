package tdv.teclasunidos.test.testNg.resources.DataProvidersCsv;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataProviderReservaCSV extends DataProviderCSV{

    public DataProviderReservaCSV(){
        super("src/tdv/teclasunidos/test/testNg/resources/csv/reservas.csv");
    }

    @Override
    public List<Object[]> parsearDatosCsv() throws IOException {
        List<String[]> datosCrudos = leerDatosDesdeCSV();
        List<Object[]> datosProcesados = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        for (String[] data : datosCrudos) {
            // Convierte la fecha de String a LocalDateTime
            LocalDateTime fechaInicio = LocalDateTime.parse(data[0], formatter);
            LocalDateTime fechaFin = LocalDateTime.parse(data[1], formatter);

            datosProcesados.add(new Object[]{fechaInicio, fechaFin});
        }

        return datosProcesados;
    }


}
