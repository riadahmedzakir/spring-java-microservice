package com.silver.ws.identityservice.application.controllers;

import javax.validation.Valid;

import com.silver.ws.identityservice.application.models.CreateUserRequestModel;
import com.silver.ws.identityservice.application.service.UserService;
import com.silver.ws.identityservice.application.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identity")
public class UserController {

    @Autowired
    private UserService _userService;

    @GetMapping("/status/check")
    public String status() {
        return "working";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel user){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        _userService.createUser(userDto);

        return "User Created";
    }
}
