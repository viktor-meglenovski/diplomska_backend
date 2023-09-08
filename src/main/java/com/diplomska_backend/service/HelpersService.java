package com.diplomska_backend.service;

import com.diplomska_backend.model.enums.Category;
import com.diplomska_backend.model.enums.Stores;

import java.util.List;

public interface HelpersService {

    List<Category> getAllCategories();
    List<Stores> getAllStores();
}
