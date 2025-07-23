package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.modelDTO.UserDTO;
import com.example.demo.dto.requestDTO.UpdateUserRequestDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Theme;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(UserDTO::new)
        .collect(Collectors.toList());
  }

  public UserDTO getUserById(ObjectId id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return new UserDTO(user);
  }

  public UserDTO getUserByIdWithAccessCheck(ObjectId id, ObjectId currentUserId) {
    if (!id.equals(currentUserId)) {
      throw new AccessDeniedException("Access denied");
    }
    return getUserById(id);
  }

  public UserDTO updateUserLang(ObjectId id, ObjectId currentUserId, String lang) {
    if (!id.equals(currentUserId)) {
      throw new AccessDeniedException("Access denied");
    }
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setLang(lang);
    return new UserDTO(userRepository.save(user));
  }

  public UserDTO updateUserTheme(ObjectId id, ObjectId currentUserId, Theme theme) {
    if (!id.equals(currentUserId)) {
      throw new AccessDeniedException("Access denied");
    }
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setTheme(theme);
    return new UserDTO(userRepository.save(user));
  }

  public void deleteUserWithAccessCheck(ObjectId id, ObjectId currentUserId) {
    if (!id.equals(currentUserId)) {
      throw new AccessDeniedException("Access denied");
    }
    userRepository.deleteById(id);
  }

  public UserDTO updateUser(ObjectId id, UpdateUserRequestDTO request, MultipartFile avatarFile) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (request.getUsername() != null && !request.getUsername().isBlank()) {
        user.setUsername(request.getUsername());
    }

    if (avatarFile != null && !avatarFile.isEmpty()) {
        String fileUrl = saveAvatarToDisk(avatarFile);
        user.setAvatar(fileUrl);
    } else if (request.getAvatar() != null && !request.getAvatar().isBlank()) {
        user.setAvatar(request.getAvatar());
    }

    if (request.getPassword() != null && !request.getPassword().isBlank()) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    return new UserDTO(userRepository.save(user));
}

  private String saveAvatarToDisk(MultipartFile file) {
    try {
        String uploadDir = "uploads/avatars/";
        Files.createDirectories(Paths.get(uploadDir));
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return "/avatars/" + filename;
    } catch (IOException e) {
        throw new RuntimeException("Failed to save avatar", e);
    }
}

  public UserDTO updateUserRole(ObjectId id, Role newRole) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setRole(newRole);
    return new UserDTO(userRepository.save(user));
  }

  public void deleteOwnAccount(ObjectId userId) {
    userRepository.deleteById(userId);
  }
}
