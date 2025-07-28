package sistema.tad.Pila;

public class Pila<T> implements IPila<T> {

    private NodoPila<T> tope;
    private int tamanio;

    public Pila() {
        tope = null;
        tamanio = 0;
    }


    //METODOS OVERRIDE

    @Override
    public void push(T dato) {
        if(dato == null) return;
        NodoPila<T> nuevo = new NodoPila<>(dato, tope);
        tope = nuevo;
        tamanio++;
    }

    @Override
    public T top() {
        if(esVacia()){
            throw new IllegalStateException("Pila vacia");
        }
        return tope.getDato();
    }

    @Override
    public T pop() {
        if(esVacia()) {
            throw new IllegalStateException("Pila vacia");
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    @Override
    public boolean esVacia() {
        return tope == null;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }
}