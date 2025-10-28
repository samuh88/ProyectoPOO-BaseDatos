package org.example.SubsistemaComercial;

public class LineaCompra {
    private int cantidad;
    private double precioUnit, subtotal;

    public LineaCompra(int cantidad, double precioUnit){
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }
}
