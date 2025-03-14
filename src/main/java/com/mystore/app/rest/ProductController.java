package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Product> products = productService.getAllProducts(page, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        return p != null ? new ResponseEntity<>(p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        return p != null ? new ResponseEntity<>(p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.filterByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<Product>> filterProductsByPrice(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Product> products = productService.filterByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/stock")
    public ResponseEntity<List<Product>> filterProductsByStock(
            @RequestParam Integer minStock,
            @RequestParam Integer maxStock) {
        List<Product> products = productService.filterByStock(minStock, maxStock);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}