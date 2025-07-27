package sky.project.mapper;

import constant.UserConstant;
import entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //用户登录
    public User userLoginMapper(String openid);
    //添加新用户
    public void addUserMapper(User user);
    //查找用户
    public User selectUserMapper(Long id);
}
