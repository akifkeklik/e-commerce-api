package com.youtubespring.ecommerce2.order.impl;

import com.youtubespring.ecommerce2.user.impl.User;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String orderNumber;
    public double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
