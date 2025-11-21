package com.example.examenMocJMR.controller;

import com.example.examenMocJMR.entity.Producto;
import com.example.examenMocJMR.service.ProductoService;
import com.example.examenMocJMR.service.ProductoServiceImpl;
import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @PostMapping(value = "/productos")
    public Producto addProducto(@RequestBody Producto producto) {
        return this.productoServiceImpl.addProducto(producto);
    }

    @DeleteMapping(value = "/producto/{productoId}")
    public void deleteProducto(@PathVariable Long productoId) {
        productoServiceImpl.eliminarProductoById(productoId);
    }

    @PutMapping(value = "/producto/{productoId}")
    public Producto modificarProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return this.productoServiceImpl.modificarProducto(productoId,producto);

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
            return this.productoServiceImpl.findAllProductos();
        }else
        if(precio!=0.0 && categoria.equals("")){
            return this.productoServiceImpl.findByPrecio(precio);
        }else
        if(precio==0.0 && !categoria.equals("")){
            return this.productoServiceImpl.findByCategoria(categoria);
        }else{
            return this.productoServiceImpl.findAllProductos();
        }
    }

    @GetMapping(value = "/producto/{productoId}")
    public Optional<Producto> getProducto(@PathVariable Long productoId) {
        return this.productoServiceImpl.findProducto(productoId);
    }

}