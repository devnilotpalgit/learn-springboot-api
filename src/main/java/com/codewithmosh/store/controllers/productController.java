package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.productMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class productController {
    private final ProductRepository productRepository;
    private final productMapper productMapper;

    @GetMapping
    public Iterable<ProductDto> allProductInfo(
            @RequestParam(name = "categoryId", required = false) Byte categoryId
    ){
        List<Product> products;
        if(categoryId != null){
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllProductWithCategory();
        }
        return products
                .stream()
                .map(productMapper :: toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductbyId(@PathVariable Long id, HttpMethod httpMethod){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(productMapper.toDto(product));
    }
}
