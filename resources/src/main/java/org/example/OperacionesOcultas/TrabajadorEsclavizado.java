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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFechaCaptura(String fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public int getID() {
        return ID;
    }

    public int getEdad() {
        return edad;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public String getFechaCaptura() {
        return fechaCaptura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public String getSalud() {
        return salud;
    }
}
