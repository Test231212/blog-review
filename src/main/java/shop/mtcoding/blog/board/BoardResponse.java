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

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String username;
        private boolean isBoardOwner;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.username = board.getUser().getUsername(); // join 해서 가져왔음
            this.isBoardOwner = board.isBoardOwner();
        }
    }

    }
