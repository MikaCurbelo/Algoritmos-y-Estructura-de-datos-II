
package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test09ListarViajerosPorRango {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();


    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }


    @Test
    void listarError1() {
        retorno = s.listarViajerosDeUnRangoAscendente(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.listarViajerosDeUnRangoAscendente(-5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.listarViajerosDeUnRangoAscendente(-100);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void listarError2() {
        retorno = s.listarViajerosDeUnRangoAscendente(14);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.listarViajerosDeUnRangoAscendente(50);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.listarViajerosDeUnRangoAscendente(75);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void listarVacio() {
        for (int i = 0; i < 14; i++) {
            retorno = s.listarViajerosDeUnRangoAscendente(i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals("", retorno.getValorString());
        }
    }

    @Test
    void listar1ViajeroEnRango3() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);

        for (int i = 0; i < 3; i++) {
            retorno = s.listarViajerosDeUnRangoAscendente(i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals("", retorno.getValorString());
        }

        retorno = s.listarViajerosDeUnRangoAscendente(3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());

        for (int i = 4; i < 14; i++) {
            retorno = s.listarViajerosDeUnRangoAscendente(i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals("", retorno.getValorString());
        }
    }

    @Test
    void listarSoloRango2() {
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("314.689-5", "Estandar1", "estandar1@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("4.314.689-5", "Estandar2", "estandar2@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("5.314.689-5", "Estandar3", "estandar3@ort.edu.uy", 20, Categoria.ESTANDAR);

        retorno = s.listarViajerosDeUnRangoAscendente(0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        String salidaEsperada = estandar1 + separador + ana + separador + estandar2 + separador + estandar3;
        assertEquals(salidaEsperada, retorno.getValorString());

        for (int i = 3; i < 14; i++) {
            retorno = s.listarViajerosDeUnRangoAscendente(i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals("", retorno.getValorString());
        }
    }

    @Test
    void listarPorRango() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("314.689-5", "Estandar1", "estandar1@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("4.314.689-5", "Estandar2", "estandar2@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("5.314.689-5", "Estandar3", "estandar3@ort.edu.uy", 20, Categoria.ESTANDAR);
        String salidaEsperadaRango2 = estandar1 + separador + ana + separador + estandar2 + separador + estandar3;

        retorno = s.listarViajerosDeUnRangoAscendente(0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperadaRango2, retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(maria, retorno.getValorString());

        for (int i = 5; i < 14; i++) {
            retorno = s.listarViajerosDeUnRangoAscendente(i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
            assertEquals("", retorno.getValorString());
        }

    }


}
