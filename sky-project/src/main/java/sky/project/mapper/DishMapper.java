package sky.project.mapper;

import annotation.AutoFillAspect;
import com.github.pagehelper.Page;
import dto.DishPageDTO;
import entity.Dish;
import enums.OperStat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {
    //菜品的分页查询
    public Page<Dish> dishPageMapper(DishPageDTO dishPageDTO);
    //新增菜品基本信息
    @AutoFillAspect(OperStat.INSERT)
    public void addDishMapper(Dish dish);
}
