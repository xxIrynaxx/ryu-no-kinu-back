package com.example.demo.service;

import com.example.demo.dto.modelDTO.AdminProductDTO;

import java.util.List;

public interface AdminProductService {
    List<AdminProductDTO> getAllProducts();
    AdminProductDTO getProductById(String id);
    AdminProductDTO createProduct(AdminProductDTO productDTO);
    AdminProductDTO updateProduct(String id, AdminProductDTO productDTO);
    void deleteProduct(String id);
}
