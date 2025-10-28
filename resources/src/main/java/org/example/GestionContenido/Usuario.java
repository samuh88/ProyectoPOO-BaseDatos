package org.example.GestionContenido;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int ID, passwordHash;
    protected String nombre, email, rol, estadoCuenta;

    public Usuario(int ID, String nombre, String email, String rol, String estadoCuenta, int passwordHash){
        this.ID = ID;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.estadoCuenta = estadoCuenta;
        this.passwordHash = passwordHash;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", estadoCuenta='" + estadoCuenta + '\'' +
                '}';
    }

}
