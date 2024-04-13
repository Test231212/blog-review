package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {
    @Data
    public static class LoginDTO {
        private User user;

        public LoginDTO(User user) {
            this.user = user;
        }
    }
}
