package sistema.tad.Lista;

import java.util.function.Consumer;

public interface ILista<T> {
    public void insertar(T dato);
    public void insertarOrdenado(T dato);
    public int largo();
    public boolean existe(T dato);
    public T recuperar(int posicion);
    public boolean esVacia();
    public void recorrer(Consumer<T> visitante);
    public void eliminar(int posicion);
}
