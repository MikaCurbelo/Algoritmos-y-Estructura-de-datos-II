package sistema;

import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RegistrarConexion {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(15);
    }

    @Test
    void registrarConexionOk() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarConexionError1() {
        retorno = s.registrarConexion("", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("   ", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion(null, "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void registrarConexionError2() {
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void registrarConexionError3() {
        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    @Test
    void registrarConexionError4() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }


    @Test
    void registrarConexionCompleto() {

        retorno = s.registrarConexion("", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("   ", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion(null, "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("COD01", "Ciudad1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarCiudad("COD02", "Ciudad2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }


}
