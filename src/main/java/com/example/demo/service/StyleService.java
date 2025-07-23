package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modelDTO.StyleDTO;
import com.example.demo.model.Style;
import com.example.demo.repository.StyleRepository;

@Service
public class StyleService {
  
  @Autowired
  private StyleRepository styleRepository;

  public List<StyleDTO> getAllStyles(String lang) {
    return styleRepository.findAll().stream()
        .map(style -> new StyleDTO(style, lang))
        .collect(Collectors.toList());
  }
  
  public StyleDTO getStyleById(String id, String lang) {
    Style style = styleRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Style not found"));
    return new StyleDTO(style, lang);
  }

  public Style createStyle(Style style) {
    if (styleRepository.findByCode(style.getCode()).isPresent()) {
      throw new IllegalArgumentException("A style with this code already exists");
    }

    return styleRepository.save(style);
  }

  public Style updateStyle(String id, Style updatedStyle) {
    Style existing = styleRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Style not found"));

    if (!existing.getCode().equals(updatedStyle.getCode())) {
      styleRepository.findByCode(updatedStyle.getCode()).ifPresent(s -> {
        throw new IllegalArgumentException("The new code is already in use");
      });
    }

    existing.setCode(updatedStyle.getCode());
    existing.setTranslations(updatedStyle.getTranslations());

    return styleRepository.save(existing);
  }
  
  public void deleteStyle(String id) {
    Style style = styleRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Style not found"));
    styleRepository.delete(style);
  }
}
