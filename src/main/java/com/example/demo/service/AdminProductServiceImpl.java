package com.example.demo.service;

import com.example.demo.dto.modelDTO.AdminProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<AdminProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminProductDTO getProductById(String id) {
        Product product = productRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDTO(product);
    }

    @Override
    public AdminProductDTO createProduct(AdminProductDTO dto) {
        Product product = mapToEntity(dto);
        productRepository.save(product);
        return mapToDTO(product);
    }

    @Override
    public AdminProductDTO updateProduct(String id, AdminProductDTO dto) {
        Product existing = productRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product updated = mapToEntity(dto);
        updated.setId(existing.getId());
        productRepository.save(updated);
        return mapToDTO(updated);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(new ObjectId(id));
    }

    private AdminProductDTO mapToDTO(Product product) {
        AdminProductDTO dto = new AdminProductDTO();
        dto.setId(product.getId().toHexString());
        dto.setCode(product.getCode());
        dto.setCategoryId(product.getCategoryId().toHexString());
        dto.setPrice(product.getPrice());
        dto.setAvailable(product.isAvailable());
        dto.setTranslations(product.getTranslations());
        dto.setVariants(product.getVariants());
        dto.setPhotos(product.getPhotos());
        dto.setReviews(product.getReviews());
        return dto;
    }

    private Product mapToEntity(AdminProductDTO dto) {
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setCategoryId(new ObjectId(dto.getCategoryId()));
        product.setPrice(dto.getPrice());
        product.setAvailable(dto.isAvailable());
        product.setTranslations(dto.getTranslations());
        product.setVariants(dto.getVariants());
        product.setPhotos(dto.getPhotos());
        product.setReviews(dto.getReviews());
        return product;
    }
}