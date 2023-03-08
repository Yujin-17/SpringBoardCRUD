package com.example.board.entity;

import com.example.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Board(BoardRequestDto requestDto, User user){
        this.username = user.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

//    public Board(BoardRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.author = requestDto.getAuthor();
//        this.content = requestDto.getContent();
//        this.password = requestDto.getPassword();
//    }
//
//    public void getBoardId(BoardRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.author = requestDto.getAuthor();
//        this.content = requestDto.getContent();
//        this.password = requestDto.getPassword();
//    }
//
//    public void update(BoardRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.author = requestDto.getAuthor();
//        this.content = requestDto.getContent();
//    }

//    public void delete(BoardRequestPW requestPW){
//        this.password = requestPW.getPassword();
//    }
}
