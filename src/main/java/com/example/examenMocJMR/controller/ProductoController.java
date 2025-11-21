package com.example.examenMocJMR.controller;

import com.example.examenMocJMR.entity.Producto;
import com.example.examenMocJMR.service.ProductoService;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/productos")
    public Producto addProducto(@RequestBody Producto producto) {
        return this.productoService.addProducto(producto);
    }

    @DeleteMapping(value = "/producto/{productoId}")
    public void deleteProducto(@PathVariable Long productoId) {
    }

    @PutMapping(value = "/producto/{productoId}")
    public Producto modificarProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return this.productoService.modificarProducto(productoId,producto);

    }

    @GetMapping(value = "/productos")
    public List<Producto> getProductos(@RequestParam(defaultValue = "0.0") Float precio,
                                       @RequestParam(defaultValue = "") String categoria) {
        /*
            - Si no se indica ni precio ni categoría -> obtener todos los productos.

            - Si se indica el precio -> obtener los productos con ese precio.
            - Si se indica la categoria -> obtener los productos con esa categoria.
         */
        if (precio==0.0 && categoria.equals("")){
            return this.productoService.findAllProductos();
        }else
        if(precio!=0.0 && categoria.equals("")){
            return this.productoService.findByPrecio(precio);
        }else
        if(precio==0.0 && !categoria.equals("")){
            return this.productoService.findByCategoria(categoria);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/producto/{productoId}")
    public Optional<Producto> getProducto(@PathVariable Long productoId) {
        return this.productoService.findProducto(productoId);
    }

}