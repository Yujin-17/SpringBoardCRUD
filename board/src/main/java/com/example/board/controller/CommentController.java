package com.example.board.controller;

import com.example.board.dto.CommentRequestDto;
import com.example.board.dto.CommentResponseDto;
import com.example.board.dto.CommentUpdateRequestDto;
import com.example.board.dto.MsgCodeResponseDto;
import com.example.board.security.UserDetailsImpl;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, commentUpdateRequestDto,userDetails.getUser());
    }

    @DeleteMapping("/comment/{id}")
    public MsgCodeResponseDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }
}
