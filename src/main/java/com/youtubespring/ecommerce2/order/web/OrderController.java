package com.youtubespring.ecommerce2.order.web;

import com.youtubespring.ecommerce2.order.api.OrderDto;
import com.youtubespring.ecommerce2.order.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        OrderDto dto = new OrderDto();
        dto.setOrderNumber(request.orderNumber);
        dto.setTotalAmount(request.totalAmount);

        OrderDto savedDto = orderService.save(dto);

        OrderResponse response = new OrderResponse();
        response.orderNumber = savedDto.getOrderNumber();
        response.totalAmount = savedDto.getTotalAmount();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Integer id) {
        OrderDto savedDto = orderService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        OrderResponse response = new OrderResponse();
        response.orderNumber = savedDto.getOrderNumber();
        response.totalAmount = savedDto.getTotalAmount();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderDto> list = orderService.findAll();
        List<OrderResponse> responseList = new ArrayList<>();
        for (OrderDto savedDto : list) {
            OrderResponse response = new OrderResponse();
            response.orderNumber = savedDto.getOrderNumber();
            response.totalAmount = savedDto.getTotalAmount();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Integer id, @RequestBody OrderRequest request) {
        OrderDto dto = new OrderDto();
        dto.setOrderNumber(request.orderNumber);
        dto.setTotalAmount(request.totalAmount);

        OrderDto updatedDto = orderService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        OrderResponse response = new OrderResponse();
        response.orderNumber = updatedDto.getOrderNumber();
        response.totalAmount = updatedDto.getTotalAmount();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
