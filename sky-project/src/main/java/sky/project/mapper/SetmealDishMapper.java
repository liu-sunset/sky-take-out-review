package sky.project.mapper;

import entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    //新增套餐餐品信息
    public void addSetmealDishMapper(List<SetmealDish> setmealDishList);
    //查询套餐菜品信息
    public List<SetmealDish> selectBySetmealIdMapper(Long id);
    //删除套餐的所有菜品
    public void deleteSetmealDishMapper(Long id);
    //删除套餐菜品信息
    public void deleteAllSetmealDishMapper(List<Long> ids);
}
