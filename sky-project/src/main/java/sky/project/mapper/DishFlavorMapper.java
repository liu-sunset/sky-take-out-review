package sky.project.mapper;

import entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper{
    //新增口味信息
    public void addDishFlavorMapper(List<DishFlavor> dishFlavorList);
    //根据菜品ID查询
    public List<DishFlavor> selectByDishIdMapper(long id);
    //根据菜品ID删除所有口味
    public void delectByDishIdMapper(long id);
    //批量删除所有口味
    public void deleteDishFlavorMapper(List<Long> ids);
}
