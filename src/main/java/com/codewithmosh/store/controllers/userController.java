package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.userMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class userController {
    private final UserRepository userRepository;
    private final userMapper userMapper;

    @GetMapping
    public Iterable<UserDto> allUserInfo(
          @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
       if (!Set.of("name", "email").contains(sort))
           sort = "name";
        return userRepository.findAll(Sort.by(sort))
                .stream()
                //.map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .map(userMapper :: toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id, HttpMethod httpMethod){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        //return new ResponseEntity<>(user, HttpStatus.OK);
        //var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
