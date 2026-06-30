package com.youtubespring.ecommerce2.user.impl;

import com.youtubespring.ecommerce2.order.impl.Order;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;
    public String lastName;
    public String email;
    public String passsword;
    public int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Order> orders;
}
