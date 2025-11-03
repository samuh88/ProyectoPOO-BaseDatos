package org.example.Produccion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Fabrica")
@Data
@NoArgsConstructor

public class Fabrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id, capacidad, nivelAutomatizacion;
    private String pais, ciudad;

    public Fabrica(int id, int capacidad, int nivelAutomatizado, String pais, String ciudad){
        this.id = id;
        this.capacidad = capacidad;
        this.nivelAutomatizacion = nivelAutomatizado;
        this.pais = pais;
        this.ciudad = ciudad;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNivelAutomatizacion(int nivelAutomatizacion) {
        this.nivelAutomatizacion = nivelAutomatizacion;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getNivelAutomatizacion() {
        return nivelAutomatizacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

}
