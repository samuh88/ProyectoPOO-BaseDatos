package org.example.SubsistemaComercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> getAllCarts() {
        return carritoRepository.findAll();
    }

    public Carrito saveCarrito(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public Optional<Carrito> getCarritoById(Integer Id){
        return carritoRepository.findById(Id);
    }


    public Carrito updateCarritoInfo(Integer Id, Carrito nuevaInfoCarrito){
        return carritoRepository.findById(Id).map(
                carrito ->{
                    carrito.setId (nuevaInfoCarrito.getId());
                    carrito.setFechaCreacion(nuevaInfoCarrito.getFechaCreacion());
                    return carritoRepository.save(carrito);
                }
        ).orElse(null);
    }


    public boolean deleteCartInfo (Integer Id){
        if(carritoRepository.existsById(Id)){
            carritoRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }
}
