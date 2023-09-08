package com.diplomska_backend.web;

import com.diplomska_backend.model.dto.PaginationInfo;
import com.diplomska_backend.model.dto.ProductClusterDto;
import com.diplomska_backend.model.entities.ProductCluster;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.service.ProductClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productCluster")
public class ProductClusterController {

    private final ProductClusterService productClusterService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductClusterDto>> getAll(@RequestParam Integer pageNumber){
        List<ProductClusterDto> response= productClusterService.getAll(pageNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductClusterDto>> getAllContainingProductNameLike(@RequestParam String name){
        List<ProductClusterDto> response= productClusterService.getAllContainingProductNameLike(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductClusterDto>> getAllByCategory(@RequestParam Category category){
        List<ProductClusterDto> response= productClusterService.getAllByCategory(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/store")
    public ResponseEntity<List<ProductClusterDto>> getAllContainingProductFromStore(@RequestParam Stores store){
        List<ProductClusterDto> response= productClusterService.getAllContainingProductFromStore(store);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductClusterDto>> filterProductClusters(@RequestParam @Nullable String name,
                                                                      @RequestParam @Nullable Category category,
                                                                      @RequestParam @Nullable Stores store,
                                                                      @RequestParam @Nullable Long lowerPrice,
                                                                      @RequestParam @Nullable Long upperPrice,
                                                                         @RequestParam Integer pageNumber){
        List<ProductClusterDto> response= productClusterService.filterProductClusters(name, category,store,lowerPrice, upperPrice, pageNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/paginationInfo")
    public ResponseEntity<PaginationInfo> getPaginationInfoForFilteredProductClusters(@RequestParam @Nullable String name,
                                                                                      @RequestParam @Nullable Category category,
                                                                                      @RequestParam @Nullable Stores store,
                                                                                      @RequestParam @Nullable Long lowerPrice,
                                                                                      @RequestParam @Nullable Long upperPrice) {
        PaginationInfo paginationInfo = productClusterService.getPaginationInfo(name, category, store, lowerPrice, upperPrice);
        return ResponseEntity.ok(paginationInfo);
    }
}
