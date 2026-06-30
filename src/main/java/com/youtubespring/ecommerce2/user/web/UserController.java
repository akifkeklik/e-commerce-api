package com.youtubespring.ecommerce2.user.web;

import com.youtubespring.ecommerce2.user.api.UserDto;
import com.youtubespring.ecommerce2.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserDto dto = new UserDto();
        dto.setName(request.name);
        dto.setLastName(request.lastName);
        dto.setEmail(request.email);
        dto.setPasssword(request.passsword);
        dto.setAge(request.age);

        UserDto savedDto = userService.save(dto);

        UserResponse response = new UserResponse();
        response.id = savedDto.getId();
        response.name = savedDto.getName();
        response.lastName = savedDto.getLastName();
        response.email = savedDto.getEmail();
        response.age = savedDto.getAge();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        UserDto savedDto = userService.find(id);
        if (savedDto == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse response = new UserResponse();
        response.id = savedDto.getId();
        response.name = savedDto.getName();
        response.lastName = savedDto.getLastName();
        response.email = savedDto.getEmail();
        response.age = savedDto.getAge();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDto> list = userService.findAll();
        List<UserResponse> responseList = new ArrayList<>();
        for (UserDto savedDto : list) {
            UserResponse response = new UserResponse();
            response.id = savedDto.getId();
            response.name = savedDto.getName();
            response.lastName = savedDto.getLastName();
            response.email = savedDto.getEmail();
            response.age = savedDto.getAge();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserRequest request) {
        UserDto dto = new UserDto();
        dto.setName(request.name);
        dto.setLastName(request.lastName);
        dto.setEmail(request.email);
        dto.setPasssword(request.passsword);
        dto.setAge(request.age);

        UserDto updatedDto = userService.update(id, dto);
        if (updatedDto == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse response = new UserResponse();
        response.id = updatedDto.getId();
        response.name = updatedDto.getName();
        response.lastName = updatedDto.getLastName();
        response.email = updatedDto.getEmail();
        response.age = updatedDto.getAge();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
