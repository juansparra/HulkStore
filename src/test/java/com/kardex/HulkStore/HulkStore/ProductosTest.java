package com.kardex.HulkStore.HulkStore;

import com.kardex.HulkStore.HulkStore.Entity.Producto;
import com.kardex.HulkStore.HulkStore.Repository.ProductosRepository;
import com.kardex.HulkStore.HulkStore.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static  org.junit.jupiter.api.Assertions.assertNotNull;
import static  org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductosTest {
    @Autowired
    private ProductosRepository productosRepository;

    @Test
    @Rollback(value = false)
    public void testGuardarProductos(){
        Producto producto = new Producto("Prueba","1", 1200F, 12F,1);
        Producto productoGuardado =  productosRepository.save(producto);
       assertNotNull(productoGuardado);

    }

    @Test
    @Rollback(value = false)
    public void testGuardarProductos2(){
        Producto producto = new Producto("test","2", 2400F, 32F,2);
        Producto productoGuardado =  productosRepository.save(producto);
        assertNotNull(productoGuardado);

    }

    @Test
    @Rollback(value = false)
    public void testGuardarProductos3(){
        Producto producto = new Producto("eliminado","3", 2400F, 32F,3);
        Producto productoGuardado =  productosRepository.save(producto);
        assertNotNull(productoGuardado);

    }
    @Test
    public void buscarProductoPorCodigo(){
        String codigo = "2";
        Producto producto = productosRepository.findFirstByCodigo(codigo);
        assertEquals(producto.getCodigo(),codigo);
    }


    @Test
    public void buscarProductoPorId(){
        int id = 1;
        Optional<Producto> producto = productosRepository.findById(id);
assertNotNull(producto);
    }
    @Rollback(value = false)
    @Test
    public void actualizar(){
        String nombreNuevo = "pruebaTest";
        Producto producto = new Producto(nombreNuevo,"200", 1200F, 12F,1);
        producto.setId(1);
        productosRepository.save(producto);
        Producto productoActualizado = productosRepository.findByNombre(nombreNuevo);
        assertEquals(productoActualizado.getNombre(),nombreNuevo);
    }

    @Test
    public void listarProducto(){
        List<Producto> productos = (List<Producto>) productosRepository.findAll();
        for (Producto producto : productos){
            System.out.println(producto);
        }
       assertNotNull(productos);
    }
    @Test
    public void eliminar(){
    Integer id = 3;
    boolean existeIdAntes = productosRepository.findById(id).isPresent();
    productosRepository.deleteById(id);

    boolean noExisteIdDespues = productosRepository.findById(id).isPresent();
    assertTrue(existeIdAntes);
assertFalse(noExisteIdDespues);
    }
}
