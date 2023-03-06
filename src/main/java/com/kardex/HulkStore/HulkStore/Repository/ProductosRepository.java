package com.kardex.HulkStore.HulkStore.Repository;

import com.kardex.HulkStore.HulkStore.Entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {
    Producto findFirstByCodigo(String codigo);
    Producto findByNombre(String nombre);
}
