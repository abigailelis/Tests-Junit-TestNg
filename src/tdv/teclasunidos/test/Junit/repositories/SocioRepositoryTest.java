package tdv.teclasunidos.test.Junit.repositories;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import tdv.teclasunidos.entities.DniInvalidoException;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.repositories.SocioRepository;

public class SocioRepositoryTest {

    static SocioRepository socioRepository = new SocioRepository();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("SocioRepository -> BeforeClass : setUpBeforeClass");
        Socio socio1 = new Socio ("Juan", 25, "calle 25", "1245678");
        Socio socio2 = new Socio("Pedro", 18, "calle 30", "125673");

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
    public void TestAgregar () throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        //Verificar que el socio que se agregó sea el mismo
        Socio socio = agregarSocio();
        Socio socioObtenido = socioRepository.buscarPorDni( socio.getDni() );

        Assert.assertEquals("El socio no se agregó correctamente ", socio, socioObtenido);
    }

    @Test
    @DisplayName("Eliminar socio")
    public void TestEliminar() throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        Socio socio = agregarSocio();
        //Verifico que se agregó
        Socio socioObtenido = socioRepository.buscarPorDni(socio.getDni());
        System.out.println(socioObtenido);

        socioRepository.eliminar(socio.getDni());

       socioObtenido = socioRepository.buscarPorDni(socio.getDni());

       Assert.assertNull("El socio no se eliminó correctamente", socioObtenido);

    }

    @Test
    @DisplayName("Actualizar socio")
    public void TestActualizar() throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        //Agrego un socio
        Socio socio = agregarSocio();

        //Edito el socio
        socio.setNombre("Paulo");
        socioRepository.actualizar(socio);

        //Obtengo el socio editado del repository
        Socio socioActual = socioRepository.buscarPorDni( socio.getDni() );

        Assert.assertEquals("El socio no se actualizó correctamente", socio, socioActual);
    }

    @Test
    @DisplayName("Verificar nombre muy largo")
    public void TestNombreMuyLargo() throws EdadInvalidaException, NombreMuyLargoException {
        String nombre = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEF";

        //Verifica que realmente se largue la exception del constructor de Socio
        Assert.assertThrows ( NombreMuyLargoException.class, () -> new Socio(nombre, 28, "Calle Bourdeu", "1234567") );
    }

    @Test
    @DisplayName("Verificar dni")
    public void TestDniValido() {
        String dniSinPuntos = "123456";
        String dniInvalido = "12.345.789";

        //Verifica que se lance la exception del constructor de Socio
        Assert.assertThrows ( DniInvalidoException.class, () -> new Socio("Pepe", 28, "Calle Bourdeu", dniInvalido) );
    }

    //Función auxiliar para agregar un socio
    public Socio agregarSocio() throws EdadInvalidaException, NombreMuyLargoException, DniInvalidoException {
        Socio socio =  new Socio("Pepe", 28, "Calle Bourdeu", "1234567");
        socioRepository.agregar(socio);

        return socio;
    }
}
