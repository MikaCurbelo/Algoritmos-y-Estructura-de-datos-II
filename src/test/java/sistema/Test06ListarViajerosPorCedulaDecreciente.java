
package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test06ListarViajerosPorCedulaDecreciente {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();



    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarVacio() {
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listar1Viajero() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());
    }

    @Test
    void listarSolo2Viajeros() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ana+ separador +guille, retorno.getValorString());
    }


    @Test
    void listarPorCedulaDecrecienteNumerica() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);

        String salidaEsperada = guille+ separador +pedro;
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);

        salidaEsperada = maria+ separador +ana+ separador +guille+ separador +pedro;
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

    }





}
