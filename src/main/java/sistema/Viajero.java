package sistema;

import interfaz.Categoria;

public class Viajero implements Comparable<Viajero>{
    private String cedula;
    private String nombre;
    private String correo;
    private int edad;
    private Categoria categoria;

    public Viajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = categoria;
    }


    //GETTERS SETTERS
    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return cedula + ";" +
                nombre + ";" +
                correo + ";" +
                edad + ";" +
                categoria.getTexto();
    }

    @Override
    public int compareTo(Viajero otro) {
        return this.cedula.compareTo(otro.cedula);
    }
}
