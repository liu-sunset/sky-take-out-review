package sky.project.controller.admin;

import dto.CatePageDTO;
import dto.CategoryDTO;
import entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.CategoryService;
import vo.CatePageVO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDTO categoryDTO){
        log.info("新增套餐:{}",categoryDTO);
        categoryService.addCategoryService(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result catePageController(CatePageDTO catePageDTO){
        log.info("分类的分页查询:{}",catePageDTO);
        CatePageVO catePageVO = categoryService.catePageService(catePageDTO);
        return Result.success(catePageVO);
    }

    @PostMapping("/status/{status}")
    public Result modifyCateStatusController(Long id,@PathVariable Integer status){
        log.info("修改分类{}状态为{}",id,status);
        categoryService.modifyCateStatusService(id,status);
        return Result.success();
    }

    @PutMapping
    @CacheEvict(cacheNames = "categoryCache",key = "#categoryDTO.getId()")
    public Result modifyCateController(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类:{}",categoryDTO);
        categoryService.modifyCateService(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "categoryCache",key = "#id")
    public Result deleteCateController(Long id){
        log.info("删除分类:{}",id);
        categoryService.deleteCateService(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectByTypeController(Integer type){
        log.info("查找type是{}的分类",type);
        List<Category> categoryList = categoryService.selectByTypeService(type);
        return Result.success(categoryList);
    }
}
