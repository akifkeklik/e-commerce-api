package com.youtubespring.ecommerce2.store.impl;

import com.youtubespring.ecommerce2.store.api.StoreDto;
import com.youtubespring.ecommerce2.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    private StoreDto toDto(Store entity) {
        if (entity == null) return null;
        StoreDto dto = new StoreDto();
        dto.setName(entity.name);
        dto.setLocation(entity.location);
        return dto;
    }

    private Store toEntity(StoreDto dto) {
        if (dto == null) return null;
        Store entity = new Store();
        entity.name = dto.getName();
        entity.location = dto.getLocation();
        return entity;
    }

    @Override
    public StoreDto save(StoreDto dto) {
        Store entity = toEntity(dto);
        Store saved = storeRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public StoreDto update(Integer id, StoreDto dto) {
        Store existingStore = storeRepository.findById(id).orElse(null);
        if (existingStore == null) {
            return null;
        }
        existingStore.name = dto.getName();
        existingStore.location = dto.getLocation();
        Store saved = storeRepository.save(existingStore);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public StoreDto find(Integer id) {
        Store entity = storeRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<StoreDto> findAll() {
        List<Store> list = storeRepository.findAll();
        List<StoreDto> dtoList = new ArrayList<>();
        for (Store s : list) {
            dtoList.add(toDto(s));
        }
        return dtoList;
    }
}
