package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Integer id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product p = existingProduct.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setCategory(product.getCategory());
            p.setStockQuantity(product.getStockQuantity());
            return productRepository.save(p);
        }
        return null;
    }

    public String deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return "Product Deleted Successfully";
        }
        return "Product Not Found";
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> filterByPrice(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> filterByStock(Integer minStock, Integer maxStock) {
        return productRepository.findByStockQuantityBetween(minStock, maxStock);
    }
}
