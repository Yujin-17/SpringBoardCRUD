package com.example.board.controller;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/api/posts")
    public List<Board> getBoards(){
        return boardService.getBoards();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Board> getBoardId(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return  boardService.getBoardId(id, requestDto);
    }

    @PostMapping("/api/post")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @PutMapping("/api/post/{id}")
    public Optional<Board> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.update(id, boardRequestDto.getPassword(), boardRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public BoardResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.deleteBoard(id, boardRequestDto.getPassword());
    }






}
