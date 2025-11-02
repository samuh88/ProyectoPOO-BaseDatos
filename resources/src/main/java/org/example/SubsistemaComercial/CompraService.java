package org.example.SubsistemaComercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAllSales() {
        return compraRepository.findAll();
    }

    public Compra saveSales(Compra compra){
        return compraRepository.save(compra);
    }

    public Optional<Compra> getSaleById(Integer Id){
        return compraRepository.findById(Id);
    }


    public Compra updateSalesInfo(Integer Id, Compra nuevaInfoCompra){
        return compraRepository.findById(Id).map(
                compra ->{
                    compra.setId(nuevaInfoCompra.getId());
                    compra.setFecha(nuevaInfoCompra.getFecha());
                    compra.setTotal(nuevaInfoCompra.getTotal());
                    compra.setEstado(nuevaInfoCompra.getEstado());
                    return compraRepository.save(compra);
                }
        ).orElse(null);
    }


    public boolean deleteSalesInfo (Integer Id){
        if(compraRepository.existsById(Id)){
            compraRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}
