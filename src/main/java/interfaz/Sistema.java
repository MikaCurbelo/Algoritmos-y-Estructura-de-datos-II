package interfaz;

/**
 * Provee una interfaz para interactuar con el sistema
 */
public interface Sistema {


    Retorno inicializarSistema(int maxCiudades);
    Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria);
    Retorno buscarViajeroPorCedula(String cedula);
    Retorno buscarViajeroPorCorreo(String correo);
    Retorno listarViajerosPorCedulaAscendente();
    Retorno listarViajerosPorCedulaDescendente();
    Retorno listarViajerosPorCorreoAscendente();
    Retorno listarViajerosPorCategoria(Categoria unaCategoria);
    Retorno listarViajerosDeUnRangoAscendente(int rango);
    Retorno registrarCiudad(String codigo, String nombre);
    Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino);
    Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino,String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo);
    Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo);
    Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad);
    Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido);
    Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido);
}
