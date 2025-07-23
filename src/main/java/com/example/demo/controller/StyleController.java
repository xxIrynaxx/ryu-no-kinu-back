package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.modelDTO.StyleDTO;
import com.example.demo.model.Style;
import com.example.demo.service.StyleService;

@RestController
@RequestMapping("/api/styles")
public class StyleController {

  @Autowired
  private StyleService styleService;

  @GetMapping
  public List<StyleDTO> getAllStyles(@RequestParam(defaultValue = "ua") String lang) {
    return styleService.getAllStyles(lang);
  }

  @GetMapping("/{id}")
  public StyleDTO getStyleById(@PathVariable String id, @RequestParam(defaultValue = "ua") String lang) {
    return styleService.getStyleById(id, lang);
  }

  @PostMapping
  public Style createStyle(@RequestBody Style style) {
    return styleService.createStyle(style);
  }

  @PutMapping("/{id}")
  public Style updateStyle(@PathVariable String id, @RequestBody Style style) {
    return styleService.updateStyle(id, style);
  }

  @DeleteMapping("/{id}")
  public void deleteStyle(@PathVariable String id) {
    styleService.deleteStyle(id);
  }
}
