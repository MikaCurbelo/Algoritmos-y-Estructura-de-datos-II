package sistema.tad.Pila;

public class NodoPila<T> {
    private T dato;
    private NodoPila<T> siguiente;

    public NodoPila(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public NodoPila(T dato, NodoPila<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    //GETTERS SETTERS

    public T getDato() {
        return dato;
    }

    public NodoPila<T> getSiguiente() {
        return siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoPila<T> siguiente) {
        this.siguiente = siguiente;
    }
}
