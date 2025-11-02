package org.example.GestionContenido;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("AdministradorUsuario")

public class AdministradorUsuario extends Usuario{
    private int nivelAcceso;

    public AdministradorUsuario(String nombre, String email, String rol, String estadoCuenta,
                                int ID, int passwordHash, int nivelAcceso){
        super( ID,nombre, email, rol, estadoCuenta, passwordHash);
        this.nivelAcceso = nivelAcceso;

    }
}
