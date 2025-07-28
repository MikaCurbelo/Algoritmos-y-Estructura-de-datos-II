package sistema.tad.Cola;

public class NodoCola<T> {
    private T dato;
    private NodoCola<T> siguiente;

    public NodoCola(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    public NodoCola(T dato, NodoCola<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    //GETTERS SETTERS


    public T getDato() {
        return dato;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
