package com.youtubespring.ecommerce2.company.web;

import com.youtubespring.ecommerce2.company.api.CompanyDto;
import com.youtubespring.ecommerce2.company.api.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody CompanyRequest request) {
        CompanyDto dto = new CompanyDto();
        dto.setName(request.name);
        dto.setAddress(request.address);
        dto.setPhone(request.phone);

        CompanyDto savedDto = companyService.save(dto);

        CompanyResponse response = new CompanyResponse();
        response.name = savedDto.getName();
        response.address = savedDto.getAddress();
        response.phone = savedDto.getPhone();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Integer id) {
        CompanyDto savedDto = companyService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        CompanyResponse response = new CompanyResponse();
        response.name = savedDto.getName();
        response.address = savedDto.getAddress();
        response.phone = savedDto.getPhone();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        List<CompanyDto> list = companyService.findAll();
        List<CompanyResponse> responseList = new ArrayList<>();
        for (CompanyDto savedDto : list) {
            CompanyResponse response = new CompanyResponse();
            response.name = savedDto.getName();
            response.address = savedDto.getAddress();
            response.phone = savedDto.getPhone();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable Integer id, @RequestBody CompanyRequest request) {
        CompanyDto dto = new CompanyDto();
        dto.setName(request.name);
        dto.setAddress(request.address);
        dto.setPhone(request.phone);

        CompanyDto updatedDto = companyService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        CompanyResponse response = new CompanyResponse();
        response.name = updatedDto.getName();
        response.address = updatedDto.getAddress();
        response.phone = updatedDto.getPhone();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
