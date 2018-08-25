package com.example.demouserproject.controller;

import com.example.demouserproject.model.entity.User;
import com.example.demouserproject.model.entity.UserType;
import com.example.demouserproject.model.web.UserRequest;
import com.example.demouserproject.model.web.UserResponse;
import com.example.demouserproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{id}")
    public UserResponse getById(@PathVariable("id") Long id) {
        return userRepository
                .findById(id)
                .map(x -> transform(x))
                .orElse(null);
    }

    private UserResponse transform(User x) {
        return UserResponse
                .builder()
                .id(x.getId())
                .lastName(x.getLastName())
                .firstName(x.getFirstName())
                .userType(x.getUserType())
                .build();
    }

    @GetMapping("/users")
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(x -> transform(x))
                .collect(Collectors.toList());
    }

    @PostMapping("/users/save")
    public UserResponse saveUser(@RequestBody @Valid UserRequest userRequest,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(x -> System.out.printf("Field: %s | Message: %s\n",
                            x.getField(),
                            x.getDefaultMessage()));
            return null;
        }

        User user = User.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .userType(UserType.PRIVILEGED_USER)
                .build();

        userRepository.save(user);

        return UserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .userType(user.getUserType())
                .build();
    }

}
