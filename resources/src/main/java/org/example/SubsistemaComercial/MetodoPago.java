package org.example.SubsistemaComercial;

public class MetodoPago {
    private int id, numeroEnmascarado;
    private String tipo, titular;

    public MetodoPago(int id, int numeroEnmascarado, String tipo, String titular){
        this.id = id;
        this.numeroEnmascarado = numeroEnmascarado;
        this.tipo = tipo;
        this.titular = titular;
    }
}
