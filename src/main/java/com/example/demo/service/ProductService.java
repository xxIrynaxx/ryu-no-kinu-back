package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modelDTO.CategoryDTO;
import com.example.demo.dto.modelDTO.ProductDTO;
import com.example.demo.dto.modelDTO.StyleDTO;
import com.example.demo.dto.modelDTO.TypeDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Discount;
import com.example.demo.model.Product;
import com.example.demo.model.Style;
import com.example.demo.model.Translation;
import com.example.demo.model.Type;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DiscountRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StyleRepository;
import com.example.demo.repository.TypeRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private DiscountRepository discountRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private StyleRepository styleRepository;

  @Autowired
  private TypeRepository typeRepository;

  public List<ProductDTO> getAllForUser(String lang) {
    List<Product> products = productRepository.findAll();
    List<Discount> discounts = discountRepository.findAll();
    Date now = new Date();

    return products.stream().map(product -> {
      Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
      CategoryDTO categoryDTO = category != null ? new CategoryDTO(category, lang) : null;

      Style style = styleRepository.findById(category != null ? category.getStyleId() : null).orElse(null);
      Type type = typeRepository.findById(category != null ? category.getTypeId() : null).orElse(null);
      StyleDTO styleDTO = style != null ? new StyleDTO(style, lang) : null;
      TypeDTO typeDTO = type != null ? new TypeDTO(type, lang) : null;

      ProductDTO dto = new ProductDTO(product, categoryDTO, styleDTO, typeDTO, lang);

      Double discountPrice = calculateDiscountPrice(product, discounts, now);
      dto.setDiscountPrice(discountPrice);

      return dto;
    }).collect(Collectors.toList());
  }

  private Double calculateDiscountPrice(Product product, List<Discount> discounts, Date now) {
    return discounts.stream()
        .filter(d -> d.getItems().contains(product.getId()))
        .filter(d -> now.after(d.getStartDate()) && now.before(d.getEndDate()))
        .findFirst()
        .map(d -> product.getPrice() * (1 - d.getPercentage() / 100))
        .orElse(null);
  }

  public ProductDTO getById(String id, String lang) {
    Product product = productRepository.findById(new ObjectId(id))
        .orElseThrow(() -> new RuntimeException("Product not found"));

    Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
    CategoryDTO categoryDTO = category != null ? new CategoryDTO(category, lang) : null;

    List<Discount> discounts = discountRepository.findAll();
    Double discountPrice = calculateDiscountPrice(product, discounts, new Date());

    Style style = styleRepository.findById(category != null ? category.getStyleId() : null).orElse(null);
    Type type = typeRepository.findById(category != null ? category.getTypeId() : null).orElse(null);
    StyleDTO styleDTO = style != null ? new StyleDTO(style, lang) : null;
    TypeDTO typeDTO = type != null ? new TypeDTO(type, lang) : null;

    ProductDTO dto = new ProductDTO(product, categoryDTO, styleDTO, typeDTO, lang);
    dto.setDiscountPrice(discountPrice);

    return dto;
  }

  public List<ProductDTO> getByCategory(String categoryId, String lang) {
    List<Product> products = productRepository.findByCategoryId(new ObjectId(categoryId));
    List<Discount> discounts = discountRepository.findAll();
    Category category = categoryRepository.findById(new ObjectId(categoryId)).orElse(null);
    CategoryDTO categoryDTO = category != null ? new CategoryDTO(category, lang) : null;
    Date now = new Date();

    return products.stream().map(product -> {
      Style style = styleRepository.findById(category != null ? category.getStyleId() : null).orElse(null);
      Type type = typeRepository.findById(category != null ? category.getTypeId() : null).orElse(null);
      StyleDTO styleDTO = style != null ? new StyleDTO(style, lang) : null;
      TypeDTO typeDTO = type != null ? new TypeDTO(type, lang) : null;

      ProductDTO dto = new ProductDTO(product, categoryDTO, styleDTO, typeDTO, lang);
      dto.setDiscountPrice(calculateDiscountPrice(product, discounts, now));
      return dto;
    }).collect(Collectors.toList());
  }

  public List<ProductDTO> searchByName(String name, String lang) {
    List<Product> products = productRepository.findAll();
    Date now = new Date();
    List<Discount> discounts = discountRepository.findAll();

    return products.stream()
        .filter(p -> {
          Translation t = p.getTranslations().getOrDefault(lang, p.getTranslations().get("ua"));
          return t != null && t.getName().toLowerCase().contains(name.toLowerCase());
        })
        .map(p -> {
          Category category = categoryRepository.findById(p.getCategoryId()).orElse(null);
          CategoryDTO categoryDTO = category != null ? new CategoryDTO(category, lang) : null;
          Style style = styleRepository.findById(category != null ? category.getStyleId() : null).orElse(null);
          Type type = typeRepository.findById(category != null ? category.getTypeId() : null).orElse(null);
          StyleDTO styleDTO = style != null ? new StyleDTO(style, lang) : null;
          TypeDTO typeDTO = type != null ? new TypeDTO(type, lang) : null;

          ProductDTO dto = new ProductDTO(p, categoryDTO, styleDTO, typeDTO, lang);
          dto.setDiscountPrice(calculateDiscountPrice(p, discounts, now));
          return dto;
        })
        .collect(Collectors.toList());
  }
  
  public List<ProductDTO> getLatestProducts(String lang) {
    List<Product> latestProducts = productRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, 5));
    List<Discount> discounts = discountRepository.findAll();
    Date now = new Date();

    return latestProducts.stream().map(product -> {
        Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
        CategoryDTO categoryDTO = category != null ? new CategoryDTO(category, lang) : null;

        Style style = styleRepository.findById(category != null ? category.getStyleId() : null).orElse(null);
        Type type = typeRepository.findById(category != null ? category.getTypeId() : null).orElse(null);
        StyleDTO styleDTO = style != null ? new StyleDTO(style, lang) : null;
        TypeDTO typeDTO = type != null ? new TypeDTO(type, lang) : null;

        ProductDTO dto = new ProductDTO(product, categoryDTO, styleDTO, typeDTO, lang);
        Double discountPrice = calculateDiscountPrice(product, discounts, now);
        dto.setDiscountPrice(discountPrice);

        return dto;
    }).collect(Collectors.toList());
}
}