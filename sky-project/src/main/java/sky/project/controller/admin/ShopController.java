package sky.project.controller.admin;

import constant.ShopConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import result.Result;

@Slf4j
@RestController("adminShopController")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Result getShopStatusController(){
        log.info("管理端获取营业状态");
        Object statusTemp = redisTemplate.opsForValue().get(ShopConstant.SHOP_STATUS);
        if (statusTemp!=null){
            int status = Integer.parseInt(statusTemp.toString());
            return Result.success(status);
        }
        return Result.error(ShopConstant.SHOP_NULL);
    }

    @PutMapping("/{status}")
    public Result setShopStatusController(@PathVariable Integer status){
        log.info("管理端设置营业状态:{}",status);
        redisTemplate.opsForValue().set(ShopConstant.SHOP_STATUS,status);
        return Result.success();
    }
}
