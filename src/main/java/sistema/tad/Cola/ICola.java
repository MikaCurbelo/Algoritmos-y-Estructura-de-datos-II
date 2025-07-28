package sistema.tad.Cola;

import java.util.function.Consumer;

public interface ICola<T> {
     void encolar(T dato);
     T desencolar();
     boolean esVacia();
     int cantidadElementos();
     T frente();
     void recorrer(Consumer<T> visitante);

}
