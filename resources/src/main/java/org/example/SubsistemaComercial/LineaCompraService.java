package org.example.SubsistemaComercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineaCompraService {

    @Autowired
    private LineaCompraRepository lineaCompraRepository;

    public List<LineaCompra> getAllSalesLine() {
        return lineaCompraRepository.findAll();
    }

    public LineaCompra saveSalesLine(LineaCompra lineaCompra){
        return lineaCompraRepository.save(lineaCompra);
    }

    public Optional<LineaCompra> getSaleLineById(Integer Id){
        return lineaCompraRepository.findById(Id);
    }


    public LineaCompra updateSalesLineInfo(Integer Id, LineaCompra nuevaInfoLineaCompra){
        return lineaCompraRepository.findById(Id).map(
                lineaCompra -> {
                    lineaCompra.setCantidad(nuevaInfoLineaCompra.getCantidad());
                    lineaCompra.setPrecioUnit(nuevaInfoLineaCompra.getPrecioUnit());
                    lineaCompra.setSubtotal(nuevaInfoLineaCompra.getSubtotal());

                    return lineaCompraRepository.save(lineaCompra);
                }
        ).orElse(null);
    }


    public boolean deleteSalesLineInfo(Integer Id){
        if(lineaCompraRepository.existsById(Id)){
            lineaCompraRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}

