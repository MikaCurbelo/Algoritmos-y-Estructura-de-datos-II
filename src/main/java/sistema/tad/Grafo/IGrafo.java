package sistema.tad.Grafo;

import interfaz.TipoVuelo;
import interfaz.TipoVueloPermitido;
import sistema.Ciudad;
import sistema.tad.Lista.Lista;


public interface IGrafo {
    void agregarCiudad(String codigo, String nombre);
    Ciudad buscarCiudad(String codigo);
    void agregarConexion(String ciudadDeOrigen, String ciudadDeDestino);
    void agregarVuelo(String ciudadDeOrigen, String ciudadDeDestino,
                      String codigoDeVuelo, double combustible,
                      double minutos, double costoDolares, TipoVuelo tipoVuelo);
    void actualizarVuelo(String ciudadDeOrigen, String ciudadDeDestino,
                         String codigoDeVuelo, double combustible,
                         double minutos, double costoDolares, TipoVuelo tipoVuelo);
    Lista<Ciudad> ciudadesConEscala(String codigoCiudadOrigen, int escalas);
    Camino dijkstra(String ciudadDeOrigen,
                    String codigoCiudadDestino,
                    TipoVueloPermitido tipoPermitido,
                    boolean optimizarminutos);

}
