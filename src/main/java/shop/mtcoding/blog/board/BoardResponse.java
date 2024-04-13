package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardResponse {
    @AllArgsConstructor
    @Data
    public static class BoardAllDTO {
        private int id;
        private String title;

        public BoardAllDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    public static class SaveDTO {
        private int id;
        private String title;
        private String content;


        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }
}
