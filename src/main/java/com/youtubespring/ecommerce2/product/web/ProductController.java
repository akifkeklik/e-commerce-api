package com.youtubespring.ecommerce2.product.web;

import com.youtubespring.ecommerce2.product.api.ProductDto;
import com.youtubespring.ecommerce2.product.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductDto dto = new ProductDto();
        dto.setName(request.name);
        dto.setPrice(request.price);
        dto.setStock(request.stock);

        ProductDto savedDto = productService.save(dto);

        ProductResponse response = new ProductResponse();
        response.name = savedDto.getName();
        response.price = savedDto.getPrice();
        response.stock = savedDto.getStock();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        ProductDto savedDto = productService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        ProductResponse response = new ProductResponse();
        response.name = savedDto.getName();
        response.price = savedDto.getPrice();
        response.stock = savedDto.getStock();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductDto> list = productService.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        for (ProductDto savedDto : list) {
            ProductResponse response = new ProductResponse();
            response.name = savedDto.getName();
            response.price = savedDto.getPrice();
            response.stock = savedDto.getStock();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Integer id, @RequestBody ProductRequest request) {
        ProductDto dto = new ProductDto();
        dto.setName(request.name);
        dto.setPrice(request.price);
        dto.setStock(request.stock);

        ProductDto updatedDto = productService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        ProductResponse response = new ProductResponse();
        response.name = updatedDto.getName();
        response.price = updatedDto.getPrice();
        response.stock = updatedDto.getStock();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
