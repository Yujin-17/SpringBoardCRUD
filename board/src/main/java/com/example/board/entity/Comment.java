package com.example.board.entity;

import com.example.board.dto.CommentRequestDto;
import com.example.board.dto.CommentUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, Board board, User user){
        this.postId = board.getId();
        this.content = requestDto.getContent();
        this.username = user.getUsername();
        this.board = board;
        this.user = user;
    }

    public void update(CommentUpdateRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
