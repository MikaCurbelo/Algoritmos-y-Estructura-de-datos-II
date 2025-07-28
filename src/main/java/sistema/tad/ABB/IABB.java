package sistema.tad.ABB;

import java.util.function.Consumer;

public interface IABB<T> {
    void insertar(T dato);
    boolean pertenece(T dato);
    void inOrder(Consumer<T> visitante);
    void inOrderDesc(Consumer<T> visitante);
    int cantMayores(T k);
    T buscar(T busca);
}
