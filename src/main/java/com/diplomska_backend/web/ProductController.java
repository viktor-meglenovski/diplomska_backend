package com.diplomska_backend.web;

import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto response = productService.getById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/expectedUps")
    public ResponseEntity<List<ProductDto>> getExpectedUps(){
        List<ProductDto> response = productService.getExpectedUps();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/expectedDowns")
    public ResponseEntity<List<ProductDto>> getExpectedDowns(){
        List<ProductDto> response = productService.getExpectedDowns();
        return ResponseEntity.ok(response);
    }

}
