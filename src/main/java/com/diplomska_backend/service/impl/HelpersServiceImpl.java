package com.diplomska_backend.service.impl;

import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;
import com.diplomska_backend.service.HelpersService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HelpersServiceImpl implements HelpersService {
    @Override
    public List<Category> getAllCategories() {
        return Arrays.stream(Category.values()).toList();
    }

    @Override
    public List<Stores> getAllStores() {
        return Arrays.stream(Stores.values()).toList();
    }
}
