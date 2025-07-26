package sky.project.controller.user;

import entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.ResourceLinkRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.javapoet.ClassName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.mapper.DishMapper;
import sky.project.service.DishService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController("userDishController")
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishMapper dishMapper;

    @GetMapping("/list")
    @Cacheable(cacheNames = "categoryCache",key = "#categoryId")
    public Result userSelectDishByCateIdController(Long categoryId){
        log.info("展示cateID为{}下的菜品",categoryId);
        List<Dish> dishList = dishService.selectDishAndFlavorByCategoryId(categoryId);
        log.info("展示的分类细节:{}",dishList);
        return Result.success(dishList);
    }
}
