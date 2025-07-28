package interfaz;

import java.util.Arrays;
import java.util.Objects;

public enum TipoVuelo {
    COMERCIAL(0, "Comercial"),
    PRIVADO(1, "Privado");

    private final int indice;
    private final String texto;

    TipoVuelo(int indice, String texto) {
        this.indice = indice;
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public String getTexto() {
        return texto;
    }

    public static TipoVuelo fromTexto(String texto) {
        return Arrays.stream(TipoVuelo.values())
                .filter(a -> Objects.equals(a.texto, texto))
                .findFirst()
                .orElse(null);
    }

}
