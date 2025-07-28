package sistema;

import interfaz.Sistema;
import interfaz.TipoVuelo;
import interfaz.Retorno;
import interfaz.TipoVueloPermitido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test15CostoMinimo {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(50);
    }

    @Test
    void costoMinimoError1() {
        retorno = s.viajeCostoMinimoMinutos("", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("   ", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos(null, "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("COD01", "", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("COD01", "   ", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("COD01", null, TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void costoMinimoError2() {
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void costoMinimoError3() {
        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void costoMinimoError4() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void costoMinimoAristaDirecto() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 1500, 360, 1500, TipoVuelo.COMERCIAL);
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(360, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());
    }

    @Test
    void costoMinimoAristaDirectoActualizandoVuelo01() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 1500, 360, 1500, TipoVuelo.COMERCIAL);
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(360, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        s.actualizarVuelo("COD01", "COD02", "codigo1", 1500, 500, 2500, TipoVuelo.COMERCIAL);
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

    }

    @Test
    void costoMinimoAristaDirectoActualizandoVuelo02() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 1500, 360, 1500, TipoVuelo.PRIVADO);
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(360, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        s.actualizarVuelo("COD01", "COD02", "codigo1", 1500, 500, 2500, TipoVuelo.PRIVADO);
        retorno = s.viajeCostoMinimoMinutos("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD02", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());
    }

    @Test
    void costoMinimoVueloMinimo() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarCiudad("COD03", "Ciudad3");
        s.registrarConexion("COD01", "COD02");
        s.registrarConexion("COD01", "COD03");
        s.registrarConexion("COD02", "COD03");

        s.registrarVuelo("COD01", "COD02", "codigo1", 1500, 300, 300, TipoVuelo.COMERCIAL);
        s.registrarVuelo("COD01", "COD02", "codigo2", 1500, 200, 200, TipoVuelo.COMERCIAL);
        s.registrarVuelo("COD01", "COD02", "codigo3", 1500, 100, 100, TipoVuelo.COMERCIAL);
        s.registrarVuelo("COD01", "COD02", "codigo4", 1500, 400, 400, TipoVuelo.COMERCIAL);
        s.registrarVuelo("COD01", "COD02", "codigo5", 1500, 850, 850, TipoVuelo.COMERCIAL);

        s.registrarVuelo("COD02", "COD03", "codigo1", 1500, 100, 100, TipoVuelo.COMERCIAL);
        s.registrarVuelo("COD01", "COD03", "codigo1", 1500, 1000, 1000, TipoVuelo.COMERCIAL);

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD03", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(200, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2 + separador + ciudad3, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD03", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(200, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad2 + separador + ciudad3, retorno.getValorString());
    }

    @Test
    void costoMinimoCompleto() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarCiudad("COD03", "Ciudad3");
        s.registrarCiudad("COD04", "Ciudad4");
        s.registrarCiudad("COD05", "Ciudad5");
        s.registrarCiudad("COD06", "Ciudad6");

        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 1500, 100, 50, TipoVuelo.PRIVADO);
        s.registrarConexion("COD01", "COD03");
        s.registrarVuelo("COD01", "COD03", "codigo1", 1500, 400, 200, TipoVuelo.PRIVADO);
        s.registrarConexion("COD02", "COD03");
        s.registrarVuelo("COD02", "COD03", "codigo1", 1500, 100, 50, TipoVuelo.PRIVADO);
        s.registrarConexion("COD03", "COD04");
        s.registrarVuelo("COD03", "COD04", "codigo1", 1500, 200, 100, TipoVuelo.PRIVADO);
        s.registrarConexion("COD01", "COD04");
        s.registrarVuelo("COD01", "COD04", "codigo1", 1500, 2000, 1000, TipoVuelo.PRIVADO);
        s.registrarVuelo("COD01", "COD04", "codigo2", 1500, 1000, 500, TipoVuelo.COMERCIAL);

        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(200, retorno.getValorInteger());
        String camino = ciudad1 + separador + ciudad2 + separador + ciudad3 + separador + ciudad4;
        assertEquals(camino, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(400, retorno.getValorInteger());
        assertEquals(camino, retorno.getValorString());

        //Actualizar vuelo
        s.actualizarVuelo("COD01", "COD03", "codigo1", 1500, 100, 50, TipoVuelo.COMERCIAL);
        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(150, retorno.getValorInteger());
        String camino2 = ciudad1 + separador + ciudad3 + separador + ciudad4;
        assertEquals(camino2, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(300, retorno.getValorInteger());
        assertEquals(camino2, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(200, retorno.getValorInteger());
        assertEquals(camino, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(400, retorno.getValorInteger());
        assertEquals(camino, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(500, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad4, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1000, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad4, retorno.getValorString());

        s.registrarConexion("COD01", "COD05");
        s.registrarVuelo("COD01", "COD05", "codigo1", 1500, 100, 50, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD06");
        s.registrarVuelo("COD01", "COD06", "codigo1", 1500, 50, 50, TipoVuelo.PRIVADO);
        s.registrarConexion("COD05", "COD06");
        s.registrarVuelo("COD05", "COD06", "codigo1", 1500, 100, 50, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD06", "COD04");
        s.registrarVuelo("COD06", "COD04", "codigo1", 1500, 100, 50, TipoVuelo.COMERCIAL);

        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(150, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad5 + separador + ciudad6 + separador + ciudad4, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(300, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad5 + separador + ciudad6 + separador + ciudad4, retorno.getValorString());

        retorno = s.viajeCostoMinimoDolares("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(100, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad6 + separador + ciudad4, retorno.getValorString());

        retorno = s.viajeCostoMinimoMinutos("COD01", "COD04", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(150, retorno.getValorInteger());
        assertEquals(ciudad1 + separador + ciudad6 + separador + ciudad4, retorno.getValorString());
    }


}
