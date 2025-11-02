package org.example.Produccion;
import org.example.SubsistemaComercial.Carrito;
import org.example.SubsistemaComercial.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricaService{
    @Autowired
    private FabricaRepository fabricaRepository;

    public List<Fabrica> getAllFabric() {
        return fabricaRepository.findAll();
    }

    public Fabrica saveFabrica(Fabrica fabrica){
        return fabricaRepository.save(fabrica);
    }

    public Optional<Fabrica> getFabricaById(Integer Id){
        return fabricaRepository.findById(Id);
    }


    public Fabrica updateFabricaInfo(Integer Id, Fabrica nuevaInfoFabrica){
        return fabricaRepository.findById(Id).map(
                fabrica ->{
                    fabrica.setId(nuevaInfoFabrica.getId());
                    fabrica.setPais(nuevaInfoFabrica.getPais());
                    fabrica.setCapacidad(nuevaInfoFabrica.getCapacidad());
                    fabrica.setCiudad(nuevaInfoFabrica.getCiudad());
                    fabrica.setNivelAutomatizacion(nuevaInfoFabrica.getNivelAutomatizacion());
                    return fabricaRepository.save(fabrica);
                }
        ).orElse(null);
    }


    public boolean deleteFabricInfo (Integer Id){
        if(fabricaRepository.existsById(Id)){
            fabricaRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }
}



