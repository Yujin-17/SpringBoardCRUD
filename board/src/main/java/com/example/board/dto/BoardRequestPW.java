package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestPW {

    private String password;

    public void BoardRequestPW(String password) {
        this.password = password;
    }
}
