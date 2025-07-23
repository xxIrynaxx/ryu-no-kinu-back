package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modelDTO.TypeDTO;
import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;

@Service
public class TypeService {

  @Autowired
  private TypeRepository typeRepository;

  public List<TypeDTO> getAllTypes(String lang) {
    return typeRepository.findAll().stream()
        .map(type -> new TypeDTO(type, lang))
        .collect(Collectors.toList());
  }
  
  public TypeDTO getTypeById(String id, String lang) {
    Type type = typeRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Type not found"));
    return new TypeDTO(type, lang);
  }

  public Type createType(Type type) {
    if (typeRepository.findByCode(type.getCode()).isPresent()) {
      throw new IllegalArgumentException("A type with this code already exists");
    }

    return typeRepository.save(type);
  }

  public Type updateType(String id, Type updatedType) {
    Type existing = typeRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Type not found"));

    if (!existing.getCode().equals(updatedType.getCode())) {
      typeRepository.findByCode(updatedType.getCode()).ifPresent(s -> {
        throw new IllegalArgumentException("The new code is already in use");
      });
    }

    existing.setCode(updatedType.getCode());
    existing.setTranslations(updatedType.getTranslations());

    return typeRepository.save(existing);
  }
  
  public void deleteType(String id) {
    Type type = typeRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new NoSuchElementException("Style not found"));
    typeRepository.delete(type);
  }
}
