package com.kardex.HulkStore.HulkStore.Controller;

import com.kardex.HulkStore.HulkStore.Entity.Producto;
import com.kardex.HulkStore.HulkStore.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;


    @GetMapping(value = "/agregar")
    public String agregarProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "productos/agregar_producto";
    }

    @PostMapping(value = "/agregar")
    public String guardarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (productoService.BuscarPorCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productoService.Guardar(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }

    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoService.BuscarPorId(id).orElse(null));
        return "productos/editar_producto";
    }

    @PostMapping(value = "/editar/{id}")
    public String actualizarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        Producto posibleProductoExistente = productoService.BuscarPorCodigo(producto.getCodigo());

        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productoService.Guardar(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }


    @PostMapping(value = "/eliminar")
    public String eliminarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productoService.Eliminar(producto.getId());
        return "redirect:/productos/mostrar";
    }

    @GetMapping("/mostrar")
    public String MostrarProductos(Model model){
        model.addAttribute("productos",productoService.MostrarProductos());
        return "productos/ver_productos";
    }


}
