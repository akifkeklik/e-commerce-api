package com.youtubespring.ecommerce2.category.impl;

import com.youtubespring.ecommerce2.category.api.CategoryDto;
import com.youtubespring.ecommerce2.category.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private CategoryDto toDto(Category entity) {
        if (entity == null) return null;
        CategoryDto dto = new CategoryDto();
        dto.setName(entity.name);
        dto.setDescription(entity.description);
        return dto;
    }

    private Category toEntity(CategoryDto dto) {
        if (dto == null) return null;
        Category entity = new Category();
        entity.name = dto.getName();
        entity.description = dto.getDescription();
        return entity;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        Category entity = toEntity(dto);
        Category saved = categoryRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public CategoryDto update(Integer id, CategoryDto dto) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            return null;
        }
        existingCategory.name = dto.getName();
        existingCategory.description = dto.getDescription();
        Category saved = categoryRepository.save(existingCategory);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto find(Integer id) {
        Category entity = categoryRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryDto> dtoList = new ArrayList<>();
        for (Category c : list) {
            dtoList.add(toDto(c));
        }
        return dtoList;
    }
}
