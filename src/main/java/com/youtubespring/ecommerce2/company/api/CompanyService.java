package com.youtubespring.ecommerce2.company.api;

import java.util.List;

public interface CompanyService {
    CompanyDto save(CompanyDto dto);
    CompanyDto update(Integer id, CompanyDto dto);
    void delete(Integer id);
    CompanyDto find(Integer id);
    List<CompanyDto> findAll();
}
