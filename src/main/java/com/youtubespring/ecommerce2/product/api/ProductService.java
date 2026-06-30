package com.youtubespring.ecommerce2.product.api;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto dto);
    ProductDto update(Integer id, ProductDto dto);
    void delete(Integer id);
    ProductDto find(Integer id);
    List<ProductDto> findAll();
}
