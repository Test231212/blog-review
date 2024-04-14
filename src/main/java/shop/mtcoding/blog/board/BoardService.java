package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;

    public List<BoardResponse.BoardAllDTO> boardFindAll() {
        List<Board> boardList = boardJPARepository.findAll();
        return boardList.stream().map(BoardResponse.BoardAllDTO::new).toList();
    }

    @Transactional
    public BoardResponse.SaveDTO save(BoardRequest.SaveDTO reqDTO, User sessionUser){
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return new BoardResponse.SaveDTO(board);
    }

    public BoardResponse.DetailDTO boardDetail(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다"));

        return new BoardResponse.DetailDTO(board, sessionUser);
    }

    @Transactional
    public void update(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다"));
        if(sessionUserId != board.getUser().getId()){
            throw new Exception403("권한이 없습니다");
        }
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    }

    public Board boardOne(int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다"));
        return board;
    }

    @Transactional
    public void delete(int boardId, int sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다"));

        if(sessionUserId != board.getUser().getId()){
            throw new Exception403("권한이 없습니다");
        }

        boardJPARepository.deleteById(boardId);
    }
}
