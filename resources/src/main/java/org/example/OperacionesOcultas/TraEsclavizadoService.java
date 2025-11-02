package org.example.OperacionesOcultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraEsclavizadoService {

    @Autowired
    private TraEsclavizadoRepository traEsclavizadoRepository;

    public List<TrabajadorEsclavizado> getAllWorkers() {
        return traEsclavizadoRepository.findAll();
    }

    public TrabajadorEsclavizado saveWorker(TrabajadorEsclavizado trabajadorEsclavizado){
        return traEsclavizadoRepository.save(trabajadorEsclavizado);
    }

    public Optional<TrabajadorEsclavizado> getWorkerById(Integer Id){
        return traEsclavizadoRepository.findById(Id);
    }


    public TrabajadorEsclavizado updateWorkerInfo(Integer Id, TrabajadorEsclavizado nuevaInfoTrabajador){
        return traEsclavizadoRepository.findById(Id).map(
                trabajadorEsclavizado ->{
                    trabajadorEsclavizado.setEdad(nuevaInfoTrabajador.getEdad());
                    trabajadorEsclavizado.setPaisOrigen(nuevaInfoTrabajador.getPaisOrigen());
                    trabajadorEsclavizado.setNombre(nuevaInfoTrabajador.getNombre());
                    trabajadorEsclavizado.setSalud(nuevaInfoTrabajador.getSalud());
                    trabajadorEsclavizado.setAsignadoA(nuevaInfoTrabajador.getAsignadoA());
                    trabajadorEsclavizado.setFechaCaptura(nuevaInfoTrabajador.getFechaCaptura());
                    return traEsclavizadoRepository.save(trabajadorEsclavizado);
                }
        ).orElse(null);
    }


    public boolean deleteWorkerInfo(Integer Id){
        if(traEsclavizadoRepository.existsById(Id)){
            traEsclavizadoRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}



