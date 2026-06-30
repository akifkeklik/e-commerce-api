package com.youtubespring.ecommerce2.user.impl;

import com.youtubespring.ecommerce2.user.api.UserDto;
import com.youtubespring.ecommerce2.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    private UserDto toDto(User entity) {
        if (entity == null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.id);
        dto.setName(entity.name);
        dto.setLastName(entity.lastName);
        dto.setEmail(entity.email);
        dto.setPasssword(entity.passsword);
        dto.setAge(entity.age);
        dto.setOrders(entity.orders);
        return dto;
    }

    private User toEntity(UserDto dto) {
        if (dto == null) return null;
        User newEntity = new User();
        newEntity.id = dto.getId();
        newEntity.name = dto.getName();
        newEntity.lastName = dto.getLastName();
        newEntity.email = dto.getEmail();
        newEntity.passsword = dto.getPasssword();
        newEntity.age = dto.getAge();
        newEntity.orders = dto.getOrders();
        return newEntity;
    }

    @Override
    public UserDto save(UserDto dto) {
        User entity = toEntity(dto);
        User saved = userRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public UserDto update(Integer id, UserDto dto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.name = dto.getName();
        existingUser.lastName = dto.getLastName();
        existingUser.email = dto.getEmail();
        existingUser.passsword = dto.getPasssword();
        existingUser.age = dto.getAge();
        User saved = userRepository.save(existingUser);
        return toDto(saved);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto find(Integer id) {
        User entity = userRepository.findById(id).orElse(null);
        return toDto(entity);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> list = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User u : list) {
            dtoList.add(toDto(u));
        }
        return dtoList;
    }
}
