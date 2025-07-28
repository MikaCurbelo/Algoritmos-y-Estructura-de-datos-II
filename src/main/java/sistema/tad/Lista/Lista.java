package sistema.tad.Lista;

import java.util.function.Consumer;

public class Lista<T> implements ILista<T> {

    private NodoLista<T> inicio;
    private int cantidad;

    public Lista() {
        this.inicio = null;
        this.cantidad = 0;
    }

    //METODOS OVERRIDE
    @Override
    public void insertar(T dato) {
        if(dato == null) return;
        NodoLista<T> nodo = new NodoLista<>(dato);
        if(inicio == null) {
            inicio = nodo;
        }else{
            NodoLista<T> aux = inicio;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
        cantidad++;
    }

    @Override
    public void insertarOrdenado(T dato) {
        if (dato == null) return;
        NodoLista<T> nodo = new NodoLista<>(dato);

        if (inicio == null || ((Comparable<T>) dato).compareTo(inicio.getDato()) < 0) {
            nodo.setSiguiente(inicio);
            inicio = nodo;
        } else {
            NodoLista<T> actual = inicio;
            while (actual.getSiguiente() != null && ((Comparable<T>) dato).compareTo(actual.getSiguiente().getDato()) > 0) {
                actual = actual.getSiguiente();
            }
            nodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nodo);
        }

        cantidad++;
    }


    @Override
    public int largo() {
        return cantidad;
    }

    @Override
    public boolean existe(T dato) {
        if(dato == null) return false;
        NodoLista<T> actual = inicio;
        while(actual.getSiguiente() != null){
            if(actual.getSiguiente().equals(dato)) return true;
            actual = actual.getSiguiente();
        }
        return false;
    }


    @Override
    public T recuperar(int posicion) {
        if(posicion < 0 || posicion >= cantidad) {
            throw new IndexOutOfBoundsException("Posición no valida");//el indice lo esta fuera de rango
        }
        NodoLista<T> actual = inicio;
        for(int i =0; i<posicion; i++){
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void recorrer(Consumer<T> nodo) {
        NodoLista<T> actual = inicio;
        while (actual != null) {
            nodo.accept(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Override
    public void eliminar(int posicion) {
        if(posicion < 0 || posicion >= cantidad) {
            return;
        }

        //elimino inicio
        if(posicion == 0){
            inicio = inicio.getSiguiente();
        }else {
            NodoLista<T> anterior = inicio;
            for(int i =0; i < posicion - 1; i++){
                anterior = anterior.getSiguiente();
            }
            NodoLista<T> aEliminar = anterior.getSiguiente();
           anterior.setSiguiente(aEliminar.getSiguiente());
        }
        cantidad--;
    }


}
