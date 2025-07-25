package sky.project.service;

import dto.UserLoginDTO;
import vo.UserLoginVO;

import java.io.IOException;

public interface UserService {
    //用户登录
    public UserLoginVO userLoginService(UserLoginDTO userLoginDTO) throws IOException;
}
