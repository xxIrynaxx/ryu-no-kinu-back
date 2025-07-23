package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.modelDTO.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<CategoryDTO> getAllCategories(@RequestParam(defaultValue = "ua") String lang) {
    return categoryService.getAllCategories(lang);
  }

  @GetMapping("/{id}")
  public CategoryDTO getCategory(@PathVariable String id, @RequestParam(defaultValue = "ua") String lang) {
    return categoryService.getCategoryById(id, lang);
  }

  @PostMapping
  public Category createCategory(@RequestBody Category category) {
    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
    return categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable String id) {
    categoryService.deleteCategory(id);
  }
}