package org.example.Produccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// INCORPORAR TODA LA LÓGICA CRUD
@Service
public class DeProService {

    @Autowired
    private DeProRepository deProRepository;

    public List<DesarrolladorProducto> getAllDePro() {
        return deProRepository.findAll();
    }

    public DesarrolladorProducto saveDePro(DesarrolladorProducto desarrolladorProducto){
        return deProRepository.save(desarrolladorProducto);
    }

    public Optional<DesarrolladorProducto> getDeProById(Integer Id){
        return deProRepository.findById(Id);
    }


    public DesarrolladorProducto updateDeProInfo(Integer Id, DesarrolladorProducto nuevaInfoDePro){
        return deProRepository.findById(Id).map(
                desarrolladorProducto ->{
                    desarrolladorProducto.setEspecialidad(nuevaInfoDePro.getEspecialidad());
                    return deProRepository.save(desarrolladorProducto);
                }
        ).orElse(null);
    }


    public boolean deleteDeProInfo(Integer Id){
        if(deProRepository.existsById(Id)){
            deProRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}

