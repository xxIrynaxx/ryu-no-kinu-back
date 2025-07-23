package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.requestDTO.AuthRequestDTO;
import com.example.demo.dto.requestDTO.AuthResponseDTO;
import com.example.demo.dto.requestDTO.RegisterRequestDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthResponseDTO register(RegisterRequestDTO request) {
    if (userRepository.findByEmail(request.getEmail()) != null) {
      throw new RuntimeException("Email already registered");
    }

    if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
      throw new RuntimeException("Invalid email format");
    }

    if (request.getPassword().length() < 8) {
      throw new RuntimeException("Password must be at least 8 characters long");
    }

    if (!request.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$")) {
      throw new RuntimeException("Password must contain uppercase, lowercase, and number");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);

    User savedUser = userRepository.save(user);
    String token = jwtService.generateToken(savedUser);

    return new AuthResponseDTO(token);
  }

  public AuthResponseDTO login(AuthRequestDTO request) {
    User user = userRepository.findByEmail(request.getEmail());
    if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user);
    return new AuthResponseDTO(token);
  }
}
