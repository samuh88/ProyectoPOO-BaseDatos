package org.example.SubsistemaComercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineaCarritoService {

    @Autowired
    private LineaCarritoRepository lineaCarritoRepository;

    public List<LineaCarrito> getAllCartsLine() {
        return lineaCarritoRepository.findAll();
    }

    public LineaCarrito saveCartsLine(LineaCarrito lineaCarrito){
        return lineaCarritoRepository.save(lineaCarrito);
    }

    public Optional<LineaCarrito> getCartLineById(Integer Id){
        return lineaCarritoRepository.findById(Id);
    }


    public LineaCarrito updateCartsLineInfo(Integer Id, LineaCarrito nuevaInfoLineaCarrito){
        return lineaCarritoRepository.findById(Id).map(
                lineaCarrito -> {
                    lineaCarrito.setCantidad(nuevaInfoLineaCarrito.getCantidad());
                    lineaCarrito.setSubtotal(nuevaInfoLineaCarrito.getSubtotal());

                    return lineaCarritoRepository.save(lineaCarrito);
                }
        ).orElse(null);
    }


    public boolean deleteCartLineInfo(Integer Id){
        if(lineaCarritoRepository.existsById(Id)){
            lineaCarritoRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}
