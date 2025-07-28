package sky.project.service.impl;

import constant.MessageConstant;
import constant.UserConstant;
import dto.UserLoginDTO;
import com.alibaba.fastjson.JSONObject;
import entity.User;
import exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.JWTProperty;
import sky.project.mapper.UserMapper;
import sky.project.service.UserService;
import utils.HttpClientUtils;
import utils.JWTUtils;
import vo.UserLoginVO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JWTProperty jwtProperty;
    //用户登录
    @Override
    public UserLoginVO userLoginService(UserLoginDTO userLoginDTO) throws IOException {
        //后端发送httpclient
        String URL = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> map = new HashMap<>();
        map.put("appid","wx65426a0680d4afa6");
        map.put("secret","f17e7297d583e9f317abb0bd804eb035");
        map.put("js_code",userLoginDTO.getCode());
        map.put("grant_type","authorization_code");
        String entity = HttpClientUtils.doPost(URL, map);
        JSONObject jsonObject = JSONObject.parseObject(entity);
        String openid = jsonObject.get("openid").toString();

        if(openid!=null){
            //查询数据库
            User user = userMapper.userLoginMapper(openid);
            if (user!=null){
                Map<String,Object> claim = new HashMap<>();
                claim.put(UserConstant.USER_ID_INTERCEPTER,user.getId());
                String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), claim);
                return UserLoginVO.builder()
                        .id(user.getId())
                        .openid(user.getOpenid())
                        .token(token)
                        .build();
            }else {
                //新用户登录
                User userTemp = User.builder()
                        .openid(openid)
                        .createTime(LocalDateTime.now())
                        .build();
                userMapper.addUserMapper(userTemp);
                User userFlag = userMapper.userLoginMapper(openid);
                Map<String,Object> claim = new HashMap<>();
                claim.put(UserConstant.USER_ID_INTERCEPTER,userFlag.getId());
                String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), claim);
                return UserLoginVO.builder()
                        .id(userFlag.getId())
                        .openid(userFlag.getOpenid())
                        .token(token)
                        .build();
            }
        }
        throw new UserException(MessageConstant.LOGIN_FAIL);
    }
}
