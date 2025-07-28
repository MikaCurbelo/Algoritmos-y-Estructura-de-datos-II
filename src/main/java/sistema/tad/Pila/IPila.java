package sistema.tad.Pila;

public interface IPila<T> {
    void push(T dato);
    T top();
    T pop();
    boolean esVacia();
    int tamanio();
}
