package org.example.OperacionesOcultas;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.*;
        import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Trabajador Esclavizado")
@Data
@NoArgsConstructor

public class TrabajadorEsclavizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
