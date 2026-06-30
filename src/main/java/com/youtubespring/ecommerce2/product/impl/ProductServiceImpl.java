package com.youtubespring.ecommerce2.product.impl;

import com.youtubespring.ecommerce2.product.api.ProductDto;
import com.youtubespring.ecommerce2.product.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    private ProductDto toDto(Product entity) {
        if (entity == null) return null;
        ProductDto dto = new ProductDto();
        dto.setName(entity.name);
        dto.setPrice(entity.price);
        dto.setStock(entity.stock);
        return dto;
    }

    private Product toEntity(ProductDto dto) {
        if (dto == null) return null;
        Product entity = new Product();
        entity.name = dto.getName();
        entity.price = dto.getPrice();
        entity.stock = dto.getStock();
        return entity;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        Product entity = toEntity(dto);
        Product saved = productRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public ProductDto update(Integer id, ProductDto dto) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.name = dto.getName();
        existingProduct.price = dto.getPrice();
        existingProduct.stock = dto.getStock();
        Product saved = productRepository.save(existingProduct);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto find(Integer id) {
        Product entity = productRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> list = productRepository.findAll();
        List<ProductDto> dtoList = new ArrayList<>();
        for (Product p : list) {
            dtoList.add(toDto(p));
        }
        return dtoList;
    }
}
