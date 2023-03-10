package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {

    @NotNull(message = "username을 입력해주세요.")
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]+")
    private  String username;

    @NotNull(message = "password를 입력해주세요.")
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,15}$\n")  // validation. entity의 목적- DB와 연결하는 데이터의 모음..DTO의 목적 - Data를 움직이는 목적 외부에서 오는 데이터를 여기서 체크.
    private String password;

    private boolean admin = false;
    private String adminToken ="";
}
