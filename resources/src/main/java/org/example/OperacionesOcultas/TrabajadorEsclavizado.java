package org.example.OperacionesOcultas;

public class TrabajadorEsclavizado {
    private int ID, edad;
    private String nombre, paisOrigen, fechaCaptura, salud, asignadoA;

    public TrabajadorEsclavizado(int ID, int edad, String nombre, String paisOrigen, String fechaCaptura, String salud, String asignadoA){
        this.ID = ID;
        this.edad = edad;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.fechaCaptura = fechaCaptura;
        this.salud = salud;
        this.asignadoA = asignadoA;
    }
}
