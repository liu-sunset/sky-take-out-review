package sky.project.mapper;

import annotation.AutoFillAspect;
import com.github.pagehelper.Page;
import dto.DishPageDTO;
import entity.Dish;
import enums.OperStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {
    //菜品的分页查询
    public Page<Dish> dishPageMapper(DishPageDTO dishPageDTO);
    //新增菜品基本信息
    @AutoFillAspect(OperStat.INSERT)
    public void addDishMapper(Dish dish);
    //修改菜品
    @AutoFillAspect(OperStat.UPDATE)
    public void modifyDishMapper(Dish dish);
    //查询回显
    public Dish selectDishByIdService(Long id);
    //批量删除菜品
    public void deleteDishMapper(List<Long> ids);
    //根据分类ID查询菜品
    public List<Dish> selectByCategoryId(Long categoryId);
    //修改菜品状态
    public void modifyDishStatusMapper(Long id,Integer status);
}
