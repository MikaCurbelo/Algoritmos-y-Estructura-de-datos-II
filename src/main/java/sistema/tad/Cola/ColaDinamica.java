package sistema.tad.Cola;

import java.util.function.Consumer;

public class ColaDinamica<T> implements ICola<T> {

    private NodoCola<T> inicio;
    private NodoCola<T> fin;
    private int cantidad;

    public ColaDinamica() {
        inicio = null;
        fin = null;
        cantidad = 0;
    }


    //METODOS OVERRIDE
    @Override
    public void encolar(T dato) {
        if(dato == null) return;

        NodoCola<T> nodo = new NodoCola<>(dato);
        if(esVacia()){
            inicio = nodo;
            fin = nodo;
        }else{
            fin.setSiguiente(nodo);
            fin = nodo;
        }
        cantidad++;
    }

    @Override
    public T desencolar(){
        if(esVacia()){
            throw new IllegalStateException("Cola esta vacia");
        }
        T dato = inicio.getDato();
        inicio = inicio.getSiguiente();
        if(inicio == null){
            fin = null;
        }
        cantidad--;
        return dato;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public int cantidadElementos() {
        return cantidad;
    }

    @Override
    public T frente() {
        if(esVacia()){
            throw new IllegalStateException("Cola esta vacia");
        }
        return inicio.getDato();
    }

    @Override
    public void recorrer(Consumer<T> visitante) {
        NodoCola<T> actual = inicio;
        while(actual != null){
            visitante.accept(actual.getDato());
            actual = actual.getSiguiente();
        }
    }


}
