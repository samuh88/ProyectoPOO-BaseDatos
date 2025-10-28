package org.example.SubsistemaComercial;

public class Compra {
    private int id;
    private String fecha, estado;
    private double total;

    public Compra(int id, String fecha, double total){
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }
}
