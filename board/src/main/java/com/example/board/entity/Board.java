package com.example.board.entity;

import com.example.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy(value = "createdAt DESC ")
    private List<Comment> commentList = new ArrayList<>();
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

    public Board(String username, String title, String content){
        this.username = username;
        this.title = title;
        this.content = content;
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
