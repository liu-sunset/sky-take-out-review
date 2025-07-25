package sky.project.controller.user;

import constant.ShopConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

@Slf4j
@RestController("userShopController")
@RequestMapping("/user/shop")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Result getShopStatusController(){
        log.info("用户端获取营业状态");
        int status = Integer.parseInt(redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS).toString());
        return Result.success(status);
    }
}
