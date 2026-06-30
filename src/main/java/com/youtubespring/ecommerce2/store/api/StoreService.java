package com.youtubespring.ecommerce2.store.api;

import java.util.List;

public interface StoreService {
    StoreDto save(StoreDto dto);
    StoreDto update(Integer id, StoreDto dto);
    void delete(Integer id);
    StoreDto find(Integer id);
    List<StoreDto> findAll();
}
