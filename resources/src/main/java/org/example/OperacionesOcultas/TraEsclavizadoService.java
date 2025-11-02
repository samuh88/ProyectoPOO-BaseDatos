package org.example.OperacionesOcultas;
import org.example.GestionContenido.Usuario;
import org.example.GestionContenido.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraEsclavizadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUserById(Integer Id){
        return usuarioRepository.findById(Id);
    }


    public Usuario updateUserInfo(Integer Id, Usuario nuevaInfoUsuario){
        return usuarioRepository.findById(Id).map(
                usuario ->{
                    usuario.setID(nuevaInfoUsuario.getID());
                    usuario.setNombre(nuevaInfoUsuario.getNombre());
                    usuario.setEmail(nuevaInfoUsuario.getEmail());
                    usuario.setRol(nuevaInfoUsuario.getRol());
                    return usuarioRepository.save(usuario);
                }
        ).orElse(null);
    }


    public boolean deleteUserInfo (Integer Id){
        if(usuarioRepository.existsById(Id)){
            usuarioRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}



