package com.youtubespring.ecommerce2.user.api;

import java.util.List;

public interface UserService {
    UserDto save(UserDto dto);
    UserDto update(Integer id, UserDto dto);
    void delete(Integer id);
    UserDto find(Integer id);
    List<UserDto> findAll();
}
