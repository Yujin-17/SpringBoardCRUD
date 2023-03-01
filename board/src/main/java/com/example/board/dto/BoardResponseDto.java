package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardResponseDto {

    private String result;
    private String message;

    public void setResult(String result, String message) {
        this.result = result;
        this.message = message;
    }




}
