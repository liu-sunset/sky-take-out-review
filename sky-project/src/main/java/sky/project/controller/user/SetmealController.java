package sky.project.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.SetmealService;
import vo.SetmealVO;

@Slf4j
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    public Result selectSetmealByCateIdController(Long categoryId){
        log.info("用户根据分类ID:{}查询套餐",categoryId);
        SetmealVO setmealVO = setmealService.selectByIdService(categoryId);
        return Result.success(setmealVO);
    }

    @GetMapping("/dish/{id}")
    public Result selectSetmealBySetIdController(@PathVariable Long id){
        log.info("用户根据套餐ID:{}查询套餐",id);
        SetmealVO setmealVO = setmealService.selectByIdService(id);
        return Result.success(setmealVO);
    }
}
