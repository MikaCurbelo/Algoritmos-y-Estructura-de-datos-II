
package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test08ListarViajerosPorCategoria {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();



    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarVacio() {
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.FRECUENTE);
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listar1Viajero() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());
    }

    @Test
    void listarSolo2Viajeros() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille+ separador +maria, retorno.getValorString());
    }


    @Test
    void listarPorCategoriaSoloEstandar() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);
        s.registrarViajero("314.689-5", "Estandar1", "estandar1@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("4.314.689-5", "Estandar2", "estandar2@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("5.314.689-5", "Estandar3", "estandar3@ort.edu.uy", 20, Categoria.ESTANDAR);

        String salidaEsperada = estandar1+ separador +guille+ separador +maria+ separador +estandar2+ separador +estandar3;
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

    }

    @Test
    void listarPorCategoria() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());

        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);
        s.registrarViajero("314.689-5", "Estandar1", "estandar1@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("4.314.689-5", "Estandar2", "estandar2@ort.edu.uy", 20, Categoria.ESTANDAR);
        s.registrarViajero("5.314.689-5", "Estandar3", "estandar3@ort.edu.uy", 20, Categoria.ESTANDAR);

        String salidaEsperada = estandar1+ separador +guille+ separador +maria+ separador +estandar2+ separador +estandar3;
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

        retorno = s.listarViajerosPorCategoria(Categoria.FRECUENTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(pedro, retorno.getValorString());

        retorno = s.listarViajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ana, retorno.getValorString());

        salidaEsperada = estandar1+ separador +guille+ separador +maria+ separador +estandar2+ separador +estandar3;
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

    }


}
