package sistema;

import interfaz.TipoVuelo;

public class Vuelo {
    private String codigoCiudadOrigen;
    private String codigoCiudadDestino;
    private String codigoDeVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private TipoVuelo tipoVuelo;

    public Vuelo(String codigoCiudadOrigen,
                 String codigoCiudadDestino,
                 String codigoDeVuelo,
                 double combustible,
                 double minutos,
                 double costoEnDolares,
                 TipoVuelo tipoVuelo) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
        this.codigoCiudadDestino = codigoCiudadDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.tipoVuelo = tipoVuelo;
    }

    //GETTERS SETTERS


    public String getCodigoCiudadOrigen() {
        return codigoCiudadOrigen;
    }

    public void setCodigoCiudadOrigen(String codigoCiudadOrigen) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
    }

    public TipoVuelo getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(TipoVuelo tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public String getCodigoCiudadDestino() {
        return codigoCiudadDestino;
    }

    public void setCodigoCiudadDestino(String codigoCiudadDestino) {
        this.codigoCiudadDestino = codigoCiudadDestino;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }
}
