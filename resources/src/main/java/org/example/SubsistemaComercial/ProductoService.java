package org.example.SubsistemaComercial;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// INCORPORAR TODA LA LÓGICA CRUD
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Optional<Producto> getProductById(Integer Id){
        return productoRepository.findById(Id);
    }


    public Producto updateProductInfo(Integer Id, Producto nuevaInfoProducto){
        return productoRepository.findById(Id).map(
                producto ->{
                    producto.setPrecio(nuevaInfoProducto.getPrecio());
                    producto.setStock(nuevaInfoProducto.getStock());
                    return productoRepository.save(producto);
                }
        ).orElse(null);
    }


    public boolean deleteProductInfo (Integer Id){
        if(productoRepository.existsById(Id)){
            productoRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}