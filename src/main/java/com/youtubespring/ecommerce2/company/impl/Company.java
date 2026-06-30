package com.youtubespring.ecommerce2.company.impl;

import com.youtubespring.ecommerce2.store.impl.Store;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String name;
    public String address;
    public String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    public List<Store> stores;
}
