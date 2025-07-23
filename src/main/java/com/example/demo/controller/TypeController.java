package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.modelDTO.TypeDTO;
import com.example.demo.model.Type;
import com.example.demo.service.TypeService;

@RestController
@RequestMapping("/api/types")
public class TypeController {
  
  @Autowired
  private TypeService typeService;

    TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

  @GetMapping
  public List<TypeDTO> getAllTypes(@RequestParam(defaultValue = "ua") String lang) {
    return typeService.getAllTypes(lang);
  }

  @GetMapping("/{id}")
  public TypeDTO getTypeById(@PathVariable String id, @RequestParam(defaultValue = "ua") String lang) {
    return typeService.getTypeById(id, lang);
  }

  @PostMapping
  public Type createType(@RequestBody Type type) {
    return typeService.createType(type);
  }

  @PutMapping("/{id}")
  public Type updateType(@PathVariable String id, @RequestBody Type type) {
    return typeService.updateType(id, type);
  }

  @DeleteMapping("/{id}")
  public void deleteType(@PathVariable String id) {
    typeService.deleteType(id);
  }
}
