package org.example.SubsistemaComercial;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.GestionContenido.Usuario;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("CLIENTE")

public class Cliente extends Usuario {
    private String direccionEnvio, metodosDePago;

    public Cliente(String nombre, String email, String rol, String estadoCuenta, int ID, int passwordHash, String direccionEnvio, String metodosDePago){
        super( ID,nombre, email, rol, estadoCuenta, passwordHash);
        this.direccionEnvio = direccionEnvio;
        this.metodosDePago = metodosDePago;

    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public String getMetodosDePago() {
        return metodosDePago;
    }
}
