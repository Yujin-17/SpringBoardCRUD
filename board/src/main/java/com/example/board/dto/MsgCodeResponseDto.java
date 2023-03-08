package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgCodeResponseDto {

    private String msg;
    private String statusCode;

    public void setResult(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
