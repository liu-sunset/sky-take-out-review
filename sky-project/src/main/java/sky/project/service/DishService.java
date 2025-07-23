package sky.project.service;

import dto.DishPageDTO;
import entity.Dish;
import vo.DishPageVO;
import vo.ModifyDishVO;

import java.util.List;

public interface DishService {
    //菜品的分页查询
    public DishPageVO dishPageService(DishPageDTO dishPageDTO);
    //新增菜品
    public void addDishService(Dish dish);
    //修改菜品
    public void modifyDishService(Dish dish);
    //查询回显
    public ModifyDishVO selectDishByIdService(long id);
    //批量删除菜品
    public void deleteDishService(List<Long> ids);
    //根据分类ID查询菜品
    public List<Dish> selectByCategoryIdService(Long categoryId);
    //修改菜品状态
    public void modifyDishStatusService(Long id,Integer status);
}
