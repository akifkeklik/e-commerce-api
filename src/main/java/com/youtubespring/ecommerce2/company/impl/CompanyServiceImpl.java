package com.youtubespring.ecommerce2.company.impl;

import com.youtubespring.ecommerce2.company.api.CompanyDto;
import com.youtubespring.ecommerce2.company.api.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    private CompanyDto toDto(Company entity) {
        if (entity == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setName(entity.name);
        dto.setAddress(entity.address);
        dto.setPhone(entity.phone);
        return dto;
    }

    private Company toEntity(CompanyDto dto) {
        if (dto == null) return null;
        Company entity = new Company();
        entity.name = dto.getName();
        entity.address = dto.getAddress();
        entity.phone = dto.getPhone();
        return entity;
    }

    @Override
    public CompanyDto save(CompanyDto dto) {
        Company entity = toEntity(dto);
        Company saved = companyRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public CompanyDto update(Integer id, CompanyDto dto) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany == null) {
            return null;
        }
        existingCompany.name = dto.getName();
        existingCompany.address = dto.getAddress();
        existingCompany.phone = dto.getPhone();
        Company saved = companyRepository.save(existingCompany);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDto find(Integer id) {
        Company entity = companyRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> list = companyRepository.findAll();
        List<CompanyDto> dtoList = new ArrayList<>();
        for (Company c : list) {
            dtoList.add(toDto(c));
        }
        return dtoList;
    }
}
