package sky.project.controller.user;

import dto.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.UserService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result userLoginController(@RequestBody UserLoginDTO userLoginVO) throws IOException {
        log.info("用户登陆，授权码:{}",userLoginVO);
        userService.userLoginService(userLoginVO);
        return Result.success();
    }
}
