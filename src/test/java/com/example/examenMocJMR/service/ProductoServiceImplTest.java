package com.example.examenMocJMR.service;

import com.example.examenMocJMR.entity.Producto;
import com.example.examenMocJMR.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoServiceImpl productoService;

    @Test
    void findAllProductos() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();

        when(productoRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Producto> resultado = productoService.findAllProductos();

        assertEquals(2, resultado.size());
        verify(productoRepository).findAll();
    }

    @Test
    void findProducto() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.findProducto(1L);

        assertTrue(resultado.isPresent());
        verify(productoRepository).findById(1L);
    }

    @Test
    void findByCategoria() {
        when(productoRepository.findByCategoria("Electrónica")).thenReturn(List.of(new Producto()));

        List<Producto> resultado = productoService.findByCategoria("Electrónica");

        assertEquals(1, resultado.size());
        verify(productoRepository).findByCategoria("Electrónica");
    }

    @Test
    void findByPrecio() {
        when(productoRepository.findByPrecio(12f)).thenReturn(List.of(new Producto()));

        List<Producto> resultado = productoService.findByPrecio(12f);

        assertEquals(1, resultado.size());
        verify(productoRepository).findByPrecio(12f);
    }

    @Test
    void findByPrecioAndCategoria() {
        when(productoRepository.findByPrecioAndCategoria(12f, "Electronica")).thenReturn(List.of(new Producto()));

        List<Producto> resultado =
                productoService.findByPrecioAndCategoria(12f, "Electronica");

        assertEquals(1, resultado.size());
        verify(productoRepository).findByPrecioAndCategoria(12f, "Electronica");
    }

    @Test
    void addProducto() {
        Producto producto = new Producto();
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.addProducto(producto);

        assertNotNull(resultado);
        verify(productoRepository).save(producto);
    }

    @Test
    void eliminarProductoById() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.eliminarProductoById(1L);

        verify(productoRepository).delete(producto);
    }

    @Test
    void modificarProducto() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.modificarProducto(1L, producto);

        assertNotNull(resultado);
        verify(productoRepository).save(producto);
    }
}