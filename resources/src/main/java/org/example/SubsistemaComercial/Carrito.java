package org.example.SubsistemaComercial;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Carrito")
@Data
@NoArgsConstructor


public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fechaCreacion;

    public Carrito(int id, String fechaCreacion){
        this.id = id;
        this.fechaCreacion = fechaCreacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
}
