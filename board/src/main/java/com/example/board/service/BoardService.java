package com.example.board.service;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return  boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional(readOnly = true)
    public Optional<Board> getBoardId(Long id, BoardRequestDto requestDto){
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
//        );
//        board.getBoardId(requestDto);
        return boardRepository.findById(id);
    }

    @Transactional
    public Board createBoard(BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public Optional<Board> update(Long id, String password, BoardRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if(board.getPassword().equals(password)) { // 비밀번호 일치조건 조건문 확인
            board.update(requestDto);
            return boardRepository.findById(id);
        }else {
            return null;
        }
    }

    @Transactional
    public BoardResponseDto deleteBoard(Long id, String password){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        BoardResponseDto result = new BoardResponseDto();
        if(board.getPassword().equals(password)) { // 비밀번호 일치조건 조건문 확인
            boardRepository.deleteById(id); // 비밀번호 일치시 해당 아이디 게시글 삭제
            result.setResult("success", "게시물이 삭제되었습니다");
            return result;
        }else{
            result.setResult("failed", "비밀번호가 일치하지 않습니다");
            return result;
        }
    }
}
