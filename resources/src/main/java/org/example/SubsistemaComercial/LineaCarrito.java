package org.example.SubsistemaComercial;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Carrito")
@Data
@NoArgsConstructor

public class LineaCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cantidad;
    private double subtotal;

    public LineaCarrito(int cantidad, double subtotal){
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }
}
