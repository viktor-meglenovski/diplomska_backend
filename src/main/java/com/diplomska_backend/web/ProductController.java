package com.diplomska_backend.web;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductDto;
import com.diplomska_backend.model.entities.Product;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.PredictionResult;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/expected")
    public ResponseEntity<List<ProductDto>> getExpected(@RequestParam PredictionResult type, @RequestParam Integer pageNumber){
        List<ProductDto> response = productService.getExpected(type, pageNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/expectedPagination")
    public ResponseEntity<PaginationInfo> getExpectedPaginationInfo(@RequestParam PredictionResult type) {
        PaginationInfo paginationInfo = productService.getPaginationInfo(type);
        return ResponseEntity.ok(paginationInfo);
    }

}
