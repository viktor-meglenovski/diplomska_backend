package com.diplomska_backend.web;

import com.diplomska_backend.model.dto.ProductClusterDto;
import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.service.HelpersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helpers")
public class HelpersController {

    private final HelpersService helpersService;

    @GetMapping("/categories/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> response= helpersService.getAllCategories();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stores/all")
    public ResponseEntity<List<Stores>> getAllStores(){
        List<Stores> response= helpersService.getAllStores();
        return ResponseEntity.ok(response);
    }
}
