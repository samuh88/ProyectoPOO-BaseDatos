package org.example.Produccion;

public class Fabrica {
    private int id, capacidad, nivelAutomatizacion;
    private String pais, ciudad;

    public Fabrica(int id, int capacidad, int nivelAutomatizado, String pais, String ciudad){
        this.id = id;
        this.capacidad = capacidad;
        this.nivelAutomatizacion = nivelAutomatizado;
        this.pais = pais;
        this.ciudad = ciudad;

    }
}
