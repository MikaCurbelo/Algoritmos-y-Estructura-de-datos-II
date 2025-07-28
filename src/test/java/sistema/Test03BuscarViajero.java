
package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03BuscarViajero {
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
        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCedula("1.914.689-5");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());

        retorno = s.buscarViajeroPorCedula("2.614.689-5");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("2.614.689-5;Ana;ana@ort.edu.uy;25;Platino", retorno.getValorString());

        retorno = s.buscarViajeroPorCedula("614.689-5");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("614.689-5;Pedro;pedro@ort.edu.uy;75;Frecuente", retorno.getValorString());

        String ci;
        String usuario;
        String correo;
        String salida;
        String ciFinal = ".689-5";
        for(int i = 1; i < 10 ; i++){
            ci = "10"+i+ciFinal;
            usuario = "Usuario"+i;
            correo = "usuario"+i+"@ort.edu.uy";
            s.registrarViajero(ci, usuario, correo, 20, Categoria.ESTANDAR);

            retorno = s.buscarViajeroPorCedula(ci);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals(2+i, retorno.getValorInteger());
            salida= ci+";"+usuario+";"+correo+";20;Estándar";
            assertEquals(salida, retorno.getValorString());
        }

    }

    @Test
    void buscarViajeroError1() {
        retorno = s.buscarViajeroPorCedula("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.buscarViajeroPorCedula("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarViajeroError2() {
        retorno = s.buscarViajeroPorCedula("1914.689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914.6895");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914.689--5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1..914.689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914..689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula(".1.914.689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914.689-5.");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        }

    @Test
    void buscarViajeroError3() {
        retorno = s.buscarViajeroPorCedula("1.914.689-5");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("914.689-5");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.914.689-5");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }




}
