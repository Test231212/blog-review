package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;

    public List<BoardResponse.BoardAllDTO> boardFindAll() {
        List<Board> boardList = boardJPARepository.findAll();
        return boardList.stream().map(board -> new BoardResponse.BoardAllDTO(board)).toList();
    }
}
