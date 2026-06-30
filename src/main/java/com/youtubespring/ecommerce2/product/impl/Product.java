package com.youtubespring.ecommerce2.product.impl;

import com.youtubespring.ecommerce2.category.impl.Category;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String name;
    public double price;
    public int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;
}
