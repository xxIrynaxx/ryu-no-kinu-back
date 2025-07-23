package com.example.demo.controller;

import com.example.demo.dto.modelDTO.AdminProductDTO;
import com.example.demo.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

  @Autowired
  private AdminProductService productService;

  @GetMapping
  public List<AdminProductDTO> getAll() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public AdminProductDTO getById(@PathVariable String id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public AdminProductDTO create(@RequestBody AdminProductDTO dto) {
    return productService.createProduct(dto);
  }

  @PutMapping("/{id}")
  public AdminProductDTO update(@PathVariable String id, @RequestBody AdminProductDTO dto) {
    return productService.updateProduct(id, dto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    productService.deleteProduct(id);
  }
}