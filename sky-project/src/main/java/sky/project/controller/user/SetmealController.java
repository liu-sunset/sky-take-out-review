package sky.project.controller.user;

import entity.Setmeal;
import entity.SetmealDish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.SetmealService;
import java.util.List;

@Slf4j
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    public Result selectSetmealByCateIdController(Long categoryId){
        log.info("用户根据分类ID:{}查询套餐",categoryId);
        List<Setmeal> setmealList = setmealService.selectSetmealByCateIdService(categoryId);
        return Result.success(setmealList);
    }

    @GetMapping("/dish/{id}")
    @Cacheable(cacheNames = "setmealCache",key = "#id")
    public Result selectSetmealBySetIdController(@PathVariable Long id){
        log.info("用户根据套餐ID:{}查询套餐",id);
        List<SetmealDish> setmealDishList = setmealService.selectSetmealDishById(id);
        return Result.success(setmealDishList);
    }
}
