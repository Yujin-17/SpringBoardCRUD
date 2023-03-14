package com.example.board.controller;

import com.example.board.dto.LoginRequestDto;
import com.example.board.dto.MsgCodeResponseDto;
import com.example.board.dto.SignupRequestDto;
import com.example.board.service.CommentService;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("/signup")
    public MsgCodeResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        MsgCodeResponseDto result = new MsgCodeResponseDto();
        result.setResult("회원가입 성공", HttpStatus.OK.value());
        return result;
    }

    @ResponseBody
    @PostMapping("/login")
    public MsgCodeResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        MsgCodeResponseDto result = new MsgCodeResponseDto();
        result.setResult("로그인 성공", HttpStatus.OK.value());
        return result;
    }
}
