package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modelDTO.CategoryDTO;
import com.example.demo.dto.modelDTO.StyleDTO;
import com.example.demo.dto.modelDTO.TypeDTO;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private StyleService styleService;

  @Autowired
  private TypeService typeService;

  public List<CategoryDTO> getAllCategories(String lang) {
    return categoryRepository.findAll().stream()
      .map(category -> {
        StyleDTO styleDTO = styleService.getStyleById(category.getStyleId().toHexString(), lang);
        TypeDTO typeDTO = typeService.getTypeById(category.getTypeId().toHexString(), lang);
        return new CategoryDTO(category, styleDTO, typeDTO, lang);
      })
      .collect(Collectors.toList());
  }

  public CategoryDTO getCategoryById(String id, String lang) {
    Category category = categoryRepository.findById(new ObjectId(id))
      .orElseThrow(() -> new RuntimeException("Category not found"));
    StyleDTO styleDTO = styleService.getStyleById(category.getStyleId().toHexString(), lang);
    TypeDTO typeDTO = typeService.getTypeById(category.getTypeId().toHexString(), lang);
    return new CategoryDTO(category, styleDTO, typeDTO, lang);
  }

  public Category createCategory(Category category) {
    if (categoryRepository.existsBySlug(category.getSlug())) {
      throw new RuntimeException("Category slug already exists");
    }
    return categoryRepository.save(category);
  }

  public Category updateCategory(String id, Category updatedCategory) {
    Category existing = categoryRepository.findById(new ObjectId(id))
      .orElseThrow(() -> new RuntimeException("Category not found"));

    if (!existing.getSlug().equals(updatedCategory.getSlug())
        && categoryRepository.existsBySlug(updatedCategory.getSlug())) {
      throw new RuntimeException("Slug already exists");
    }

    existing.setSlug(updatedCategory.getSlug());
    existing.setCountry(updatedCategory.getCountry());
    existing.setStyleId(updatedCategory.getStyleId());
    existing.setTypeId(updatedCategory.getTypeId());
    existing.setTranslations(updatedCategory.getTranslations());

    return categoryRepository.save(existing);
  }

  public void deleteCategory(String id) {
    categoryRepository.deleteById(new ObjectId(id));
  }
}

