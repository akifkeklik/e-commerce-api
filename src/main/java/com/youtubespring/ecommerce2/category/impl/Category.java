package com.youtubespring.ecommerce2.category.impl;

import com.youtubespring.ecommerce2.product.impl.Product;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String name;
    public String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<Product> products;
}
