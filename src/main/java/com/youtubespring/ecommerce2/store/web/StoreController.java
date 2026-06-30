package com.youtubespring.ecommerce2.store.web;

import com.youtubespring.ecommerce2.store.api.StoreDto;
import com.youtubespring.ecommerce2.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponse> create(@RequestBody StoreRequest request) {
        StoreDto dto = new StoreDto();
        dto.setName(request.name);
        dto.setLocation(request.location);

        StoreDto savedDto = storeService.save(dto);

        StoreResponse response = new StoreResponse();
        response.name = savedDto.getName();
        response.location = savedDto.getLocation();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getById(@PathVariable Integer id) {
        StoreDto savedDto = storeService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        StoreResponse response = new StoreResponse();
        response.name = savedDto.getName();
        response.location = savedDto.getLocation();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAll() {
        List<StoreDto> list = storeService.findAll();
        List<StoreResponse> responseList = new ArrayList<>();
        for (StoreDto savedDto : list) {
            StoreResponse response = new StoreResponse();
            response.name = savedDto.getName();
            response.location = savedDto.getLocation();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponse> update(@PathVariable Integer id, @RequestBody StoreRequest request) {
        StoreDto dto = new StoreDto();
        dto.setName(request.name);
        dto.setLocation(request.location);

        StoreDto updatedDto = storeService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        StoreResponse response = new StoreResponse();
        response.name = updatedDto.getName();
        response.location = updatedDto.getLocation();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
