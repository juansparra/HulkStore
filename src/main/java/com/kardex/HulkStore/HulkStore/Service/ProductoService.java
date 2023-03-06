package com.kardex.HulkStore.HulkStore.Service;

import com.kardex.HulkStore.HulkStore.Entity.Producto;
import com.kardex.HulkStore.HulkStore.Repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {



    private ProductosRepository productosRepository;

    public ProductoService(ProductosRepository productosRepository) {
        super();
        this.productosRepository = productosRepository;
    }

    public Producto Guardar (Producto newProducto){
        return productosRepository.save(newProducto);
    }

    public void Eliminar (int id){
       productosRepository.deleteById(id);
    }

public Producto BuscarPorCodigo(String codigoProducto){
        return productosRepository.findFirstByCodigo(codigoProducto);
}

public List<Producto> MostrarProductos(){
        return (List<Producto>) productosRepository.findAll();
}

public Optional<Producto> BuscarPorId(int IdProducto){
        return productosRepository.findById(IdProducto);
}
}
