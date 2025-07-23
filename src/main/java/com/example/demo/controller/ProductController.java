package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.modelDTO.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductDTO> getAllForUser(@RequestParam(defaultValue = "ua") String lang) {
    return productService.getAllForUser(lang);
  }

  @GetMapping("/{id}")
  public ProductDTO getProductById(@PathVariable String id, @RequestParam(defaultValue = "ua") String lang) {
    return productService.getById(id, lang);
  }

  @GetMapping("/category/{categoryId}")
  public List<ProductDTO> getProductsByCategory(@PathVariable String categoryId,
      @RequestParam(defaultValue = "ua") String lang) {
    return productService.getByCategory(categoryId, lang);
  }
  
  @GetMapping("/search")
public List<ProductDTO> searchProducts(@RequestParam String query,
    @RequestParam(defaultValue = "ua") String lang) {
  return productService.searchByName(query, lang);
}

@GetMapping("/latest")
    public ResponseEntity<List<ProductDTO>> getLatestProducts(@RequestParam(defaultValue = "ua") String lang) {
        List<ProductDTO> latestProducts = productService.getLatestProducts(lang);
        return ResponseEntity.ok(latestProducts);
    }
}