package sistema;
import sistema.tad.Lista.Lista;

public class Conexion {
     private Ciudad ciudadDestino;
     private Lista<Vuelo> vuelos;

     public Conexion(Ciudad ciudadDestino) {
         this.ciudadDestino = ciudadDestino;
         this.vuelos = new Lista<>();
     }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public Lista<Vuelo> getVuelos() {
        return vuelos;
    }
}