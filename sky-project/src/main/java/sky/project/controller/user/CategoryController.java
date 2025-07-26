package sky.project.controller.user;

import entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.CategoryService;

import java.util.List;

@Slf4j
@RestController("userCategoryController")
@RequestMapping("/user/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result selectCategoryByTypeController(Integer type){
        log.info("用户根据type:{}查询",type);
        List<Category> categoryList = categoryService.selectByTypeService(type);
        return Result.success(categoryList);
    }

}
