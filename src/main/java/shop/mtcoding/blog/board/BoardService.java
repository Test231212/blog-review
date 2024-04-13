package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;

import java.util.List;

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
}
