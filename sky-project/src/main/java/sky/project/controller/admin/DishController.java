package sky.project.controller.admin;

import dto.DishPageDTO;
import entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.DishService;
import vo.DishPageVO;
import vo.ModifyDishVO;

import java.util.List;

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

    @PutMapping
    public Result modifyDishController(@RequestBody Dish dish){
        log.info("修改菜品:{}",dish);
        dishService.modifyDishService(dish);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectDishByIdController(@PathVariable Long id){
        log.info("菜品的查询回显:{}",id);
        ModifyDishVO modifyDishVO = dishService.selectDishByIdService(id);
        return Result.success(modifyDishVO);
    }

    @DeleteMapping
    public Result deleteDishController(@RequestParam List<Long> ids){
        log.info("批量删除菜品:{}",ids);
        dishService.deleteDishService(ids);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectByCategoryIdController(Long categoryId){
        log.info("根据分类ID查询菜品:{}",categoryId);
        List<Dish> dishList = dishService.selectByCategoryIdService(categoryId);
        return Result.success(dishList);
    }

    @PostMapping("/status/{status}")
    public Result modifyDishStatusController(Long id,@PathVariable Integer status){
        log.info("修改菜品ID是{}的状态为{}",id,status);
        dishService.modifyDishStatusService(id,status);
        return Result.success();
    }
}
