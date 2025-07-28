package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02RegistrarViajero {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarViajeroOk() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarViajero("2.914.689-5", "Ana", "ana@ort.edu.uy", 26, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarViajeroError1() {
        retorno = s.registrarViajero("", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("   ", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero(null, "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", null, "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", null, 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarViajeroError2() {
        retorno = s.registrarViajero("1.91.4.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1.914.6895", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914.6895", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914..689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914.689--5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914.689-5.", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero(".914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1..914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("914.689.-.5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarViajeroError3() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guilleort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "@guilleort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guilleort.edu.uy@", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy@", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "@guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void registrarViajeroError4() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", -10, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", -1, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 140, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 200, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void registrarViajeroError5() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void registrarViajeroError6() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.registrarViajero("2.914.689-4", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }

}
