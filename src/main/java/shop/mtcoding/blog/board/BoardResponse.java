package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

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
        private int userId;
        private String username;
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.isOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        @Data
        public class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername(); // lazy loading 발동
                this.isOwner = false;
                if (sessionUser != null) {
                    if (sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }
}
