package org.example.SubsistemaComercial;

import org.example.GestionContenido.Usuario;

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
