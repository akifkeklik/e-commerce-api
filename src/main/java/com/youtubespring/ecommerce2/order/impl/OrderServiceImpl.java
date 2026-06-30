package com.youtubespring.ecommerce2.order.impl;

import com.youtubespring.ecommerce2.order.api.OrderDto;
import com.youtubespring.ecommerce2.order.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    private OrderDto toDto(Order entity) {
        if (entity == null) return null;
        OrderDto dto = new OrderDto();
        dto.setOrderNumber(entity.orderNumber);
        dto.setTotalAmount(entity.totalAmount);
        return dto;
    }

    private Order toEntity(OrderDto dto) {
        if (dto == null) return null;
        Order entity = new Order();
        entity.orderNumber = dto.getOrderNumber();
        entity.totalAmount = dto.getTotalAmount();
        return entity;
    }

    @Override
    public OrderDto save(OrderDto dto) {
        Order entity = toEntity(dto);
        Order saved = orderRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public OrderDto update(Integer id, OrderDto dto) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder == null) {
            return null;
        }
        existingOrder.orderNumber = dto.getOrderNumber();
        existingOrder.totalAmount = dto.getTotalAmount();
        Order saved = orderRepository.save(existingOrder);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto find(Integer id) {
        Order entity = orderRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> list = orderRepository.findAll();
        List<OrderDto> dtoList = new ArrayList<>();
        for (Order o : list) {
            dtoList.add(toDto(o));
        }
        return dtoList;
    }
}
