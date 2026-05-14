package com.springboot.features.controller;

import com.springboot.features.dto.LoginDto;
import com.springboot.features.dto.SignUpDto;
import com.springboot.features.dto.UserDto;
import com.springboot.features.service.AuthService;
import com.springboot.features.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> singUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
