package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.modelDTO.UserDTO;
import com.example.demo.dto.requestDTO.UpdateUserRequestDTO;
import com.example.demo.dto.requestDTO.UpdateUserRoleDTO;
import com.example.demo.model.Theme;
import com.example.demo.service.UserOperationsService;
import com.example.demo.service.UserPrincipal;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {
  
  private final UserService userService;
  private final UserOperationsService userOperationsService;

  public UserController(UserService userService, UserOperationsService userOperationsService) {
    this.userService = userService;
    this.userOperationsService = userOperationsService;
  }

  @GetMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  
  @GetMapping("/users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> getUserByIed(@PathVariable ObjectId id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PatchMapping("/admin/users/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserRole(
            @PathVariable ObjectId id,
        @RequestBody UpdateUserRoleDTO roleDTO) {

      return ResponseEntity.ok(userService.updateUserRole(id, roleDTO.getRole()));
    }
    
    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
      ObjectId currentUserId = new ObjectId(principal.getName());
      return ResponseEntity.ok(userService.getUserById(currentUserId));
    }
    
    @PatchMapping("/users/lang")
    public ResponseEntity<UserDTO> updateLang(
        @RequestBody String lang,
        Principal principal) {

  ObjectId currentUserId = new ObjectId(principal.getName());
  return ResponseEntity.ok(userService.updateUserLang(currentUserId, currentUserId, lang.replace("\"", "")));
}

    @PatchMapping("/users/theme")
    public ResponseEntity<UserDTO> updateTheme(
        @RequestBody Theme theme,
        Principal principal) {

    ObjectId currentUserId = new ObjectId(principal.getName());
    return ResponseEntity.ok(userService.updateUserTheme(currentUserId, currentUserId, theme));
}
    
    @DeleteMapping("/users/{id}/delete")
    public ResponseEntity<Void> deleteUser(
            @PathVariable ObjectId id,
        Principal principal) {

      ObjectId currentUserId = new ObjectId(principal.getName());
      userService.deleteUserWithAccessCheck(id, currentUserId);
      return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/users/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<UserDTO> updateProfile(
        @RequestPart(required = false) String username,
        @RequestPart(required = false) String password,
        @RequestPart(required = false) String avatar,
        @RequestPart(required = false) MultipartFile avatarFile,
        Principal principal
) {
    ObjectId userId = new ObjectId(principal.getName());

    UpdateUserRequestDTO dto = new UpdateUserRequestDTO();
    dto.setUsername(username);
    dto.setPassword(password);
    dto.setAvatar(avatar);

    UserDTO updated = userService.updateUser(userId, dto, avatarFile);
    return ResponseEntity.ok(updated);
}

    @DeleteMapping("/users/me")
    public ResponseEntity<Void> deleteOwnAccount(Principal principal) {
      ObjectId currentUserId = new ObjectId(principal.getName());
      userService.deleteOwnAccount(currentUserId);
      return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/wishlist/{productId}")
    public ResponseEntity<UserDTO> addToWishlist(@PathVariable ObjectId productId,
                                                 @AuthenticationPrincipal UserPrincipal currentUser) {
        if (currentUser != null) {
            UserDTO updatedUser = userOperationsService.addToWishlist(currentUser.getId(), productId);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(403).build(); 
        }
    }


    @DeleteMapping("/wishlist/{productId}")
    public ResponseEntity<UserDTO> removeFromWishlist(@PathVariable ObjectId productId,
                                                      @AuthenticationPrincipal UserPrincipal currentUser) {
        if (currentUser != null) {
            UserDTO updatedUser = userOperationsService.removeFromWishlist(currentUser.getId(), productId);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(403).build(); 
        }
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<UserDTO> addToCart(@PathVariable ObjectId productId,
                                              @AuthenticationPrincipal UserPrincipal currentUser) {
        if (currentUser != null) {
            UserDTO updatedUser = userOperationsService.addToCart(currentUser.getId(), productId);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<UserDTO> removeFromCart(@PathVariable ObjectId productId,
                                                  @AuthenticationPrincipal UserPrincipal currentUser) {
        if (currentUser != null) {
            UserDTO updatedUser = userOperationsService.removeFromCart(currentUser.getId(), productId);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/recently-viewed/{productId}")
    public ResponseEntity<UserDTO> addToRecentlyViewed(@PathVariable ObjectId productId,
                                                       @AuthenticationPrincipal UserPrincipal currentUser) {
        if (currentUser != null) {
            UserDTO updatedUser = userOperationsService.addToRecentlyViewed(currentUser.getId(), productId);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/users/me/products")
    public ResponseEntity<UserDTO> getUserWithProducts(@AuthenticationPrincipal UserPrincipal currentUser,
                                                       @RequestParam String lang) {
        if (currentUser != null) {
            UserDTO userWithProducts = userOperationsService.getUserWithProducts(currentUser.getId(), lang);
            return ResponseEntity.ok(userWithProducts);
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
