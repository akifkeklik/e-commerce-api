package com.youtubespring.ecommerce2.category.api;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto dto);
    CategoryDto update(Integer id, CategoryDto dto);
    void delete(Integer id);
    CategoryDto find(Integer id);
    List<CategoryDto> findAll();
}
