package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService ;
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<BoardResponse.BoardAllDTO> boardList = boardService.boardFindAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.save(reqDTO, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{boardId}")
    public String detail(@PathVariable Integer boardId) {  // int 를 쓰면 값이 없으면 0, Integer 를 넣으면 값이 없을 때 null 값이 들어옴.
        return "board/detail";
    }

    @GetMapping("/board/{boardId}/update-form")
    public String updateForm(@PathVariable Integer boardId){return "board/update-form";}
}
