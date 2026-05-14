package com.springboot.features.service;

import com.springboot.features.dto.LoginDto;
import com.springboot.features.dto.SignUpDto;
import com.springboot.features.dto.UserDto;
import com.springboot.features.entity.User;
import com.springboot.features.exception.ResourceNotFound;
import com.springboot.features.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException("User with email "+username+ " not found"));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("User not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if (user.isPresent())
            throw new BadCredentialsException("User with email already exists");

        User toCreate = modelMapper.map(signUpDto, User.class);
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));

        User created = userRepository.save(toCreate);
        return modelMapper.map(created, UserDto.class);
    }


}
