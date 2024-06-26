package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository ;

    public User login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

    @Transactional
    public void join(UserRequest.JoinDTO reqDTO){
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if(userOP.isPresent()){
            throw new Exception400("중복된 유저네임입니다");
        }
        userJPARepository.save(reqDTO.toEntity());
    }

    @Transactional
    public User update(int id, UserRequest.UpdateDTO reqDTO){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원 정보가 없습니다"));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }
}
