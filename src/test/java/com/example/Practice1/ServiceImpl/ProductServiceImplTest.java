package com.example.Practice1.ServiceImpl;

import com.example.Practice1.DTO.ProductDto;
import com.example.Practice1.Entity.Product;
import com.example.Practice1.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {


    @Mock
    private ProductRepo productRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    void testCreateProduct(){

        ProductDto productDto = new ProductDto(null, "Laptop","Electronics",45000.0);
        Product product = new Product(null, "Laptop","Electronics",45000.0);
        Product saved = new Product(1L, "Laptop","Electronics",45000.0);
        ProductDto savedDto = new ProductDto(1L, "Laptop","Electronics",45000.0);

        when(modelMapper.map(productDto,Product.class)).thenReturn(product);
        when(productRepo.save(product)).thenReturn(saved);
        when(modelMapper.map(saved,ProductDto.class)).thenReturn(savedDto);

        ProductDto result = productService.createProduct(productDto);

        assertEquals(1L,result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals("Electronics", result.getCategory());
        assertEquals(45000.0, result.getPrice());

    }

    @Test
    void testGetAllProducts(){

        Product p1 = new Product(1L, "Phone", "Electronics", 20000.0);
        Product p2 = new Product(2L, "Laptop", "Electronics", 5000.0);

        ProductDto d1 = new ProductDto(1L, "Phone", "Electronics", 20000.0);
        ProductDto d2 = new ProductDto(2L, "Laptop", "Electronics", 5000.0);

        when(productRepo.findAll()).thenReturn(Arrays.asList(p1,p2));
        when(modelMapper.map(p1,ProductDto.class)).thenReturn(d1);
        when(modelMapper.map(p2,ProductDto.class)).thenReturn(d2);

        List<ProductDto> result = productService.getAllProducts();
        assertEquals(2,result.size());
        assertEquals("Phone",result.get(0).getName());

    }


}
