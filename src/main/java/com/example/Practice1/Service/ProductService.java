package com.example.Practice1.Service;

import com.example.Practice1.DTO.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProducts(String name);
    List<ProductDto> filterByCategory(String category);


}
