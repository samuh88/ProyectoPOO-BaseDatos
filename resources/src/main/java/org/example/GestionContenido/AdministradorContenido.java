package org.example.GestionContenido;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("AdministradorContenido")

public class AdministradorContenido extends Usuario {
    private String permisosEdicion;

    public AdministradorContenido(String nombre, String email, String rol, String estadoCuenta,
                                  int ID, int passwordHash,String permisosEdicion){
        super( ID,nombre, email, rol, estadoCuenta, passwordHash);
        this.permisosEdicion = permisosEdicion;

    }
}
