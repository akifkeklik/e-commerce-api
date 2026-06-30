package com.youtubespring.ecommerce2.store.impl;

import com.youtubespring.ecommerce2.company.impl.Company;
import jakarta.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String name;
    public String location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;
}
