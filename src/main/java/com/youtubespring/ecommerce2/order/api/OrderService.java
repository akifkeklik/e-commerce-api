package com.youtubespring.ecommerce2.order.api;

import java.util.List;

public interface OrderService {
    OrderDto save(OrderDto dto);
    OrderDto update(Integer id, OrderDto dto);
    void delete(Integer id);
    OrderDto find(Integer id);
    List<OrderDto> findAll();
}
