package sky.project.controller.admin;

import annotation.AutoFillAspect;
import dto.AddSetmealDTO;
import dto.SetmealPageDTO;
import entity.Setmeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.mapper.SetmealMapper;
import sky.project.service.SetmealService;
import vo.SetmealPageVO;
import vo.SetmealVO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealMapper setmealMapper;


    @PostMapping
    public Result addSetmealController(@RequestBody AddSetmealDTO addSetmealDTO){
        log.info("新增套餐:{}",addSetmealDTO);
        setmealService.addSetmealService(addSetmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result setmealPageController(SetmealPageDTO setmealPageDTO){
        log.info("套餐的分页查询:{}",setmealPageDTO);
        SetmealPageVO setmealPageVO = setmealService.setmealPageService(setmealPageDTO);
        return Result.success(setmealPageVO);
    }

    @GetMapping("/{id}")
    public Result selectByIdController(@PathVariable Long id){
        log.info("根据ID查询套餐:{}",id);
        SetmealVO setmealVO = setmealService.selectByIdService(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result modifySetmealController(@RequestBody Setmeal setmeal){
        log.info("修改套餐:{}",setmealMapper);
        setmealService.modifySetmealService(setmeal);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result modifySetmealStatusController(Long id,@PathVariable Integer status){
        log.info("修改套餐{}的状态为{}",id,status);
        setmealService.modifySetmealStatusService(id,status);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteSetmealController(@RequestParam List<Long> ids){
        log.info("批量删除套餐:{}",ids);
        setmealService.deleteSetmealService(ids);
        return Result.success();
    }
}
