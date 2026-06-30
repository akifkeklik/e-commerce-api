package com.youtubespring.ecommerce2.category.web;

import com.youtubespring.ecommerce2.category.api.CategoryDto;
import com.youtubespring.ecommerce2.category.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        CategoryDto dto = new CategoryDto();
        dto.setName(request.name);
        dto.setDescription(request.description);

        CategoryDto savedDto = categoryService.save(dto);

        CategoryResponse response = new CategoryResponse();
        response.name = savedDto.getName();
        response.description = savedDto.getDescription();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        CategoryDto savedDto = categoryService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        CategoryResponse response = new CategoryResponse();
        response.name = savedDto.getName();
        response.description = savedDto.getDescription();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryDto> list = categoryService.findAll();
        List<CategoryResponse> responseList = new ArrayList<>();
        for (CategoryDto savedDto : list) {
            CategoryResponse response = new CategoryResponse();
            response.name = savedDto.getName();
            response.description = savedDto.getDescription();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Integer id, @RequestBody CategoryRequest request) {
        CategoryDto dto = new CategoryDto();
        dto.setName(request.name);
        dto.setDescription(request.description);

        CategoryDto updatedDto = categoryService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        CategoryResponse response = new CategoryResponse();
        response.name = updatedDto.getName();
        response.description = updatedDto.getDescription();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
