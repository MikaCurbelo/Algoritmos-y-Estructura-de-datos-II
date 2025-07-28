package sistema.tad.ABB;

import java.util.Comparator;
import java.util.function.Consumer;


public class ABB<T> implements IABB<T> {

    private NodoABB<T> raiz;

    private final Comparator<T> comparador;

    public ABB(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public NodoABB<T> getRaiz() {
        return raiz;
    }

    public Comparator<T> getComparador() {
        return comparador;
    }

    //METODOS OVERRIDE

    @Override
    public void insertar(T dato) {
        if (dato == null) return;
        raiz = insertarRec(raiz, dato);
    }

    private NodoABB<T> insertarRec(NodoABB<T> nodo, T dato) {
        if(nodo == null) {
            return new NodoABB<>(dato);
        }
        int comp = comparador.compare(dato, nodo.getDato());
        if(comp < 0 ){
            NodoABB<T> nuevoIzq = insertarRec(nodo.getIzq(), dato);
            nodo.setIzq(nuevoIzq);
        }else if (comp > 0){
            NodoABB<T> nuevoDer = insertarRec(nodo.getDer(), dato);
            nodo.setDer(nuevoDer);
        }
        return nodo;
    }

    @Override
    public boolean pertenece(T dato) {
        return perteneceRec(raiz, dato);
    }

    private boolean perteneceRec(NodoABB<T> nodo, T dato) {
        if(nodo == null ||dato == null) return false;
        int comp = comparador.compare(dato, nodo.getDato());
        if(comp == 0) return true;
        return comp < 0 ? perteneceRec(nodo.getIzq(), dato) : perteneceRec(nodo.getDer(), dato); //si comp es menor a cero (pregunto con ?) voy por izq si no lo es (pregunto con :) voy por la derecha
    }

    @Override
    public void inOrder(Consumer<T> visitante) {
        inOrderRec(raiz, visitante);
    }

    private void inOrderRec(NodoABB<T> nodo, Consumer<T> visitante) {
        if(nodo == null) return;
        inOrderRec(nodo.getIzq(),visitante);
        visitante.accept(nodo.getDato());
        inOrderRec(nodo.getDer(),visitante);
    }

    @Override
    public void inOrderDesc(Consumer<T> visitante) {
        inOrderDescRec(raiz, visitante);
    }

    private void inOrderDescRec(NodoABB<T> nodo, Consumer<T> visitante) {
        if(nodo == null) return;
        inOrderDescRec(nodo.getDer(),visitante);
        visitante.accept(nodo.getDato());
        inOrderDescRec(nodo.getIzq(),visitante);
    }

    @Override
    public int cantMayores(T k) {
        return cantMayoresRec(raiz, k);
    }

    @Override
    public T buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private T buscarRec(NodoABB<T> nodo, T dato) {
        if(nodo == null ||dato == null) return null;
        int comp = comparador.compare(dato, nodo.getDato());
        if(comp == 0) {
            return nodo.getDato();
        } else if(comp < 0){
            return buscarRec(nodo.getIzq(),dato);
        }else{
            return buscarRec(nodo.getDer(),dato);
        }
    }

    private int cantMayoresRec(NodoABB<T> nodo, T k) {
        if(nodo == null || k == null) return 0;
        int comp = comparador.compare(k, nodo.getDato());
        if(comp <=0 ) {
            return cantMayoresRec(nodo.getDer(),k);
        }else{
            return 1 + cantMayoresRec(nodo.getIzq(),k) + cantMayoresRec(nodo.getDer(),k);
        }
    }
}
