package sistema.tad.Grafo;

import sistema.Ciudad;
import sistema.tad.Lista.Lista;

import java.util.function.Consumer;

public class Camino {
    private Lista<Ciudad> rrecorrido;
    private int costoTotal;

    public Camino(Lista<Ciudad> rrecorrido, int costoTotal) {
        this.rrecorrido = rrecorrido;
        this.costoTotal = costoTotal;
    }

    public Lista<Ciudad> getRecorrido() {
        return rrecorrido;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    @Override
    public String toString() {
       StringBuilder stB = new StringBuilder();
       rrecorrido.recorrer(new Consumer<Ciudad>() {
           @Override
           public void accept(Ciudad ciudad) {
               if(stB.length() > 0){
                   stB.append("|");
               }
               stB.append(ciudad.getCodigo())
                       .append(";")
                       .append(ciudad.getNombre());
           }
       });
       return String.format(stB.toString());
    }
}
