package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository ;

    public User 로그인(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

}
