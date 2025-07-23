package sky.project.mapper;

import entity.Dish;
import entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper{
    //新增口味信息
    public void addDishFlavorMapper(List<DishFlavor> dishFlavorList);
}
