package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Board boardDetail(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean isBoardOwner = false;
        if(sessionUser != null){
            if(Objects.equals(sessionUser.getId(), board.getUser().getId())){
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        return board;
    }
}
