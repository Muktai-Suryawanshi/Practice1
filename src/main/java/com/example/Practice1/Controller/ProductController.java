package com.example.Practice1.Controller;

import com.example.Practice1.DTO.ProductDto;
import com.example.Practice1.Entity.Product;
import com.example.Practice1.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @PostMapping
        public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
            ProductDto product = this.productService.createProduct(productDto);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<ProductDto>> getAll() {
            List<ProductDto> allProducts = this.productService.getAllProducts();
            return ResponseEntity.ok(allProducts);
        }

        @GetMapping("/search")
        public ResponseEntity<List<ProductDto>> search(@RequestParam String name) {
            List<ProductDto> productDtos = this.productService.searchProducts(name);
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        }

        @GetMapping("/filter")
        public ResponseEntity<List<ProductDto>> filterByCategory(@RequestParam String category){
            List<ProductDto> filtered = this.productService.filterByCategory(category);
            return new ResponseEntity<>(filtered,HttpStatus.OK);
        }
    }


