package sistema.tad.ABB;

public class NodoABB<T> {
    private T dato;
    private NodoABB<T> izq;
    private NodoABB<T> der;

    public NodoABB(T dato) {
        this.dato = dato;
    }

    public NodoABB(T dato, NodoABB<T> izq, NodoABB<T> der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }


    //GETTERS SETTERS
    public T getDato() {
        return dato;
    }

    public NodoABB<T> getIzq() {
        return izq;
    }

    public NodoABB<T> getDer() {
        return der;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setIzq(NodoABB<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoABB<T> der) {
        this.der = der;
    }
}
