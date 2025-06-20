package com.example.Practice1.ServiceImpl;

import com.example.Practice1.DTO.ProductDto;
import com.example.Practice1.Entity.Product;
import com.example.Practice1.Repository.ProductRepo;
import com.example.Practice1.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;


    public ProductServiceImpl(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        Product saved = productRepo.save(product);
        ProductDto mapped = this.modelMapper.map(saved, ProductDto.class);
        return mapped;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productRepo.findAll();
        List<ProductDto> collected = products.stream().map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
        return collected;
    }

    @Override
    public List<ProductDto> searchProducts(String name) {
        List<Product> products = this.productRepo.findByNameContainingIgnoreCase(name);
        List<ProductDto> collected = products.stream().map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
        return collected;
    }

    @Override
    public List<ProductDto> filterByCategory(String category) {
        List<Product> products = this.productRepo.findByCategoryIgnoreCase(category);
        List<ProductDto> collected = products.stream().map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
        return collected;
    }
}