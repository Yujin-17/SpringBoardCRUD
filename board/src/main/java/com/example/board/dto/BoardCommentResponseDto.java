package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardCommentResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();


    public BoardCommentResponseDto(Board board){
        this.id = board.getId();
        this.username = board.getUsername();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        for(Comment comment : board.getCommentList()){
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }

    }
}
