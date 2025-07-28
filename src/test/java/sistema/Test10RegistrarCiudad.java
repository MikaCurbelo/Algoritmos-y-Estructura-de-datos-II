package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarCiudad {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(15);
    }

    @Test
    void registrarCiudadOk() {
        retorno = s.registrarCiudad("COD01", "Ciudad1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarViajero("2.914.689-5", "Ana", "ana@ort.edu.uy", 26, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarCiudadError1() {
        s.inicializarSistema(5);
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarCiudad("COD03", "Ciudad3");
        s.registrarCiudad("COD04", "Ciudad4");
        s.registrarCiudad("COD05", "Ciudad5");
        retorno = s.registrarCiudad("COD06", "Ciudad6");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void registrarCiudadError2() {
        retorno = s.registrarCiudad("", "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("   ", "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad(null, "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void registrarCiudadError3() {
        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.registrarCiudad("COD01", "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    @Test
    void registrarCiudadCompleto() {

        retorno = s.registrarCiudad("", "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("   ", "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad(null, "Ciudad1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "Ciudad1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("COD02", "Ciudad2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("COD02", "Ciudad2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        for (int i = 3; i <= 15; i++) {
            retorno = s.registrarCiudad("COD" + i, "Ciudad" + i);
            assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        }

        retorno = s.registrarCiudad("COD16", "Ciudad16");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());


    }


}
