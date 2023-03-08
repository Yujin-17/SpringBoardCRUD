package com.example.board.controller;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardsResponseDto;
import com.example.board.dto.MsgCodeResponseDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


//    @GetMapping("/api/posts")
//    public List<Board> getBoards(){
//        return boardService.getBoards();
//    }
//
//    @GetMapping("/api/post/{id}")
//    public Optional<Board> getBoardId(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
//        return  boardService.getBoardId(id, requestDto);
//    }

    // 게시글 등록하기
    @PostMapping("/api/post")
    public BoardsResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        // 응답 보내기
        return boardService.createBoard(requestDto, request);
    }

    // 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<BoardsResponseDto> getBoards() {
        return boardService.getBoards();
    }

    // 게시글 상세 조회
    @GetMapping("/api/post/{id}")
    public BoardsResponseDto getBoardId(@PathVariable Long id) {
        return boardService.getBoardId(id);
    }

    // 게시글 수정
    @PutMapping("/api/post/{id}")
    public BoardsResponseDto update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.update(id, boardRequestDto, request);
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public MsgCodeResponseDto delete(@PathVariable Long id, HttpServletRequest request){
        return boardService.delete(id, request);
    }

//
//    // 게시글 상세 조회
//    @GetMapping("/api/post/{id}")
//    public

//    @PutMapping("/api/post/{id}")
//    public Optional<Board> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
//        return boardService.update(id, boardRequestDto.getPassword(), boardRequestDto);
//    }
//
//    @DeleteMapping("/api/post/{id}")
//    public BoardResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
//        return boardService.deleteBoard(id, boardRequestDto.getPassword());
//    }



}
