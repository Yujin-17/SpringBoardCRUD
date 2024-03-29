package com.example.board.service;

import com.example.board.dto.*;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.User;
import com.example.board.entity.UserRoleEnum;
import com.example.board.exception.ApiException;
import com.example.board.exception.ExceptionEnum;
import com.example.board.jwt.JwtUtil;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

//    @Transactional(readOnly = true)
//    public List<Board> getBoards() {
//        return boardRepository.findAllByOrderByModifiedAtDesc();
//    }
//


    @Transactional
    public BoardsResponseDto createBoard(BoardRequestDto requestDto, User user) {
//        // Request에서 token 가져오기
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//        // 토큰이 있는 경우에만 게시글 추가
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                // 토큰에서 사용자 정보 가져오기
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new ApiException(ExceptionEnum.INVAILD_TOKEN);
//            }
        // 토큰에서 가져온 사용자 정보를 사용해 DB 조회
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
//        );

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = boardRepository.saveAndFlush(new Board(requestDto, user));
        return new BoardsResponseDto(board);
    }

    @Transactional(readOnly = true)
    public List<BoardCommentResponseDto> getBoards() {
        List<BoardCommentResponseDto> boardsResponseDtos = new ArrayList<>();
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();

        for (Board board : boardList) {
            boardsResponseDtos.add(new BoardCommentResponseDto(board));
        }
        return boardsResponseDtos;

    }

    @Transactional(readOnly = true)
    public BoardCommentResponseDto getBoardId(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
        );

        return new BoardCommentResponseDto(board);
    }

    @Transactional
    public BoardsResponseDto update(Long id, BoardRequestDto boardRequestDto, User user) {
//        // Request에서 token 가져오기
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//        // 토큰이 있는 경우에만 게시글 추가
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                // 토큰에서 사용자 정보 가져오기
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new ApiException(ExceptionEnum.INVAILD_TOKEN);
//            }


//        // 토큰에서 가져온 사용자 정보를 사용해 DB 조회
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
//        );
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        if (board.getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN) {
            board.update(boardRequestDto);

            return new BoardsResponseDto(board);
        } else {
            return null;
        }
    }


    @Transactional
    public MsgCodeResponseDto delete(Long id, User user) {
//        // Request에서 token 가져오기
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//
//        // 토큰이 있는 경우에만 게시글 추가
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                // 토큰에서 사용자 정보 가져오기
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new ApiException(ExceptionEnum.INVAILD_TOKEN);
//            }


        // 토큰에서 가져온 사용자 정보를 사용해 DB 조회
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new ApiException(ExceptionEnum.NOT_FOUND_USER)
//        );
        MsgCodeResponseDto result = new MsgCodeResponseDto();
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        if (board.getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN) {
            commentRepository.deleteByPostId(id);
            boardRepository.deleteById(id);
            result.setResult("게시글 삭제 성공", HttpStatus.OK.value());
            return result;
        } else {
            result.setResult("게시글 삭제 실패", HttpStatus.BAD_REQUEST.value());
            ;
            return result;
        }
    }
}
//    @Transactional
//    public BoardResponseDto deleteBoard(Long id, String password){
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//
//        BoardResponseDto result = new BoardResponseDto();
//        if(board.getPassword().equals(password)) { // 비밀번호 일치조건 조건문 확인
//            boardRepository.deleteById(id); // 비밀번호 일치시 해당 아이디 게시글 삭제
//            result.setResult("success", "게시물이 삭제되었습니다");
//            return result;
//        }else{
//            result.setResult("failed", "비밀번호가 일치하지 않습니다");
//            return result;
//        }
//    }

//    @Transactional(readOnly = true)
//    public Optional<Board> getBoardId(Long id, BoardRequestDto requestDto) {
////        Board board = boardRepository.findById(id).orElseThrow(
////                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
////        );
////        board.getBoardId(requestDto);
//        return boardRepository.findById(id);
//    }
//    @Transactional
//    public Optional<Board> update(Long id, String password, BoardRequestDto requestDto){
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
//        );
//        if(board.getPassword().equals(password)) { // 비밀번호 일치조건 조건문 확인
//            board.update(requestDto);
//            return boardRepository.findById(id);
//        }else {
//            return null;
//        }
//    }







