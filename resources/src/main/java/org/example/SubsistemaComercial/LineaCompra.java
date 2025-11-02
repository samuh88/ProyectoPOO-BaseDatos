package org.example.SubsistemaComercial;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Carrito")
@Data
@NoArgsConstructor

public class LineaCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cantidad;
    private double precioUnit, subtotal;

    public LineaCompra(int cantidad, double precioUnit){
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }
}
