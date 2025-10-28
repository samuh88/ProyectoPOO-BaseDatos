package org.example.Produccion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "DesarrolladorProducto")
@Data
@NoArgsConstructor

public class DesarrolladorProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String especialidad;

    public DesarrolladorProducto(String especialidad){
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
