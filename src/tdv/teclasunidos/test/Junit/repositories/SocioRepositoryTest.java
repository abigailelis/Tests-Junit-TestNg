package tdv.teclasunidos.test.repositories;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.repositories.SocioRepository;

public class SocioRepositoryTest {

    static SocioRepository socioRepository = new SocioRepository();;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("SocioRepository -> BeforeClass : setUpBeforeClass");
        Socio socio1 = new Socio ("Juan", 25, "calle 25", "12.456.789");
        Socio socio2 = new Socio("Pedro", 18, "calle 30", "12.567.389");

        socioRepository.agregar(socio1);
        socioRepository.agregar(socio2);
    }

    @Before
    public void setUp() throws Exception{
        System.out.println("SocioRepository -> Before : setUp");
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("SocioRepository -> After : tearDown");
    }

    @Test
    @DisplayName("Agregar socio")
    public void TestAgregar () throws EdadInvalidaException, NombreMuyLargoException {
        int cantSociosInicial = getCantidadDeSocios();
        agregarSocio();
        int cantSociosFinal = getCantidadDeSocios();

        Assert.assertEquals("El socio no se agregó correctamente ", cantSociosInicial + 1, cantSociosFinal);
    }

    @Test
    @DisplayName("Eliminar socio")
    public void TestEliminar() throws EdadInvalidaException, NombreMuyLargoException {
        agregarSocio();
        int cantSociosInicial = getCantidadDeSocios();
        socioRepository.eliminar("12.345.789");
        int cantSociosFinal = getCantidadDeSocios();

         Assert.assertEquals("El socio no se eliminó correctamente", cantSociosInicial - 1, cantSociosFinal);

    }

    @Test
    @DisplayName("Actualizar socio")
    public void TestActualizar() throws EdadInvalidaException, NombreMuyLargoException {
        //Agrego un socio
        Socio socio = agregarSocio();

        //Edito el socio
        socio.setNombre("Paulo");
        socioRepository.actualizar(socio);

        //Obtengo el socio editado del repository
        Socio socioActual = socioRepository.buscarPorDni("12.345.789");

        Assert.assertEquals("El socio no se actualizó correctamente", socio, socioActual);
    }

    @Test
    @DisplayName("Verificar nombre muy largo")
    public void TestNombreMuyLargo() throws EdadInvalidaException, NombreMuyLargoException {
        Socio socio = agregarSocio();
        socio.setNombre("AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEE");
    }

    @Test
    @DisplayName("Verificar dni")
    public void TestDniValido() throws EdadInvalidaException, NombreMuyLargoException {
        //String dniSinPuntos = "123456789";
        //String dniCorto = "1.123.456";
        String dniValido = "12.345.789";
        Socio socio = new Socio("Pepe", 28, "Calle 74", dniValido);

        Assert.assertTrue("El dni del socio no es válido", verificarDniValido ( socio.getDni() ) );
    }

    //Función auxiliar para obtener la cantidad de socios
    public int getCantidadDeSocios(){
        return socioRepository.listar().size();
    }

    //Función auxiliar para agregar un socio
    public Socio agregarSocio() throws EdadInvalidaException, NombreMuyLargoException {
        Socio socio = new Socio("Pepe", 28, "Calle Bourdeu", "12.345.789");
        socioRepository.agregar(socio);
        return socio;
    }

    //Funcion auxiliar para verificar un dni
    public boolean verificarDniValido(String dni){
        // Eliminar los puntos para verificar solo los números
        String dniSinPuntos = dni.replace(".", "");

        // Verificar si tiene exactamente 8 números
        if (!dniSinPuntos.matches("\\d{8}"))
            return false;

        // Verificar si los puntos están en los lugares correctos (ej. 12.345.678)
        return dni.matches("\\d{2}\\.\\d{3}\\.\\d{3}");
    }

}
