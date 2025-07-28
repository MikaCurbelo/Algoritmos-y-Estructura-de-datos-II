
package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04BuscarViajeroPorCorreo {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);
        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());

        retorno = s.buscarViajeroPorCorreo("ana@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("2.614.689-5;Ana;ana@ort.edu.uy;25;Platino", retorno.getValorString());

        retorno = s.buscarViajeroPorCorreo("maria@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("3.614.689-5;María;maria@ort.edu.uy;45;Estándar", retorno.getValorString());

        retorno = s.buscarViajeroPorCorreo("pedro@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorInteger());
        assertEquals("614.689-5;Pedro;pedro@ort.edu.uy;75;Frecuente", retorno.getValorString());

    }

    @Test
    void buscarViajeroError1() {
        retorno = s.buscarViajeroPorCorreo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.buscarViajeroPorCorreo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarViajeroError2() {
        retorno = s.buscarViajeroPorCorreo("guilleort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("@guilleort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guilleort.edu.uy@");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("@guille@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy@");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("@guille@ort.edu.uy@");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void buscarViajeroError3() {
        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("ana@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("fede@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }




}
