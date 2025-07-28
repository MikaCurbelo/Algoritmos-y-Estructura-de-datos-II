package interfaz;

import java.util.Arrays;
import java.util.Objects;

public enum TipoVueloPermitido {
    COMERCIAL(0, "Comercial"),
    PRIVADO(1, "Privado"),
    AMBOS(2, "Ambos");

    private final int indice;
    private final String texto;

    TipoVueloPermitido(int indice, String texto) {
        this.indice = indice;
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public String getTexto() {
        return texto;
    }

    public static TipoVueloPermitido fromTexto(String texto) {
        return Arrays.stream(TipoVueloPermitido.values())
                .filter(a -> Objects.equals(a.texto, texto))
                .findFirst()
                .orElse(null);
    }

}
