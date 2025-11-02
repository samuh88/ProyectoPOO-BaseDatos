package org.example.GestionContenido;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("DUENA")

public class Dueña extends Usuario{
    private int claveMaestra;
    private String fechaCoronacion;

    public Dueña(String nombre, String email, String rol, String estadoCuenta,
                 int ID, int passwordHash, int claveMaestra, String fechaCoronacion){
        super( ID,nombre, email, rol, estadoCuenta, passwordHash);
        this.claveMaestra = claveMaestra;
        this.fechaCoronacion = fechaCoronacion;
    }

}
