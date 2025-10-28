package org.example.SubsistemaComercial;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Categoria {

    private String nombre, descripcion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public Categoria(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }
}
