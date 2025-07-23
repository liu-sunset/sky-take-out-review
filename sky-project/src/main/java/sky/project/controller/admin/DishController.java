package sky.project.controller.admin;

import dto.DishPageDTO;
import entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.DishService;
import vo.DishPageVO;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/page")
    public Result dishPageController(DishPageDTO dishPageDTO){
        log.info("菜品的分页查询:{}",dishPageDTO);
        DishPageVO dishPageVO = dishService.dishPageService(dishPageDTO);
        return Result.success(dishPageVO);
    }

    @PostMapping
    public Result addDishController(@RequestBody Dish dish){
        log.info("新增菜品:{}",dish);
        dishService.addDishService(dish);
        return Result.success();
    }
}
