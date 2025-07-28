package sistema;

import sistema.tad.Lista.ILista;
import sistema.tad.Lista.Lista;

public class Ciudad implements Comparable<Ciudad>{

    private String codigo;
    private String nombre;
    private ILista<Conexion> conexiones;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.conexiones = new Lista<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ILista<Conexion> getConexiones() {
        return this.conexiones;
    }

    @Override
    public int compareTo(Ciudad otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }
}