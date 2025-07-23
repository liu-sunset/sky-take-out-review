package sky.project.service;

import dto.DishPageDTO;
import entity.Dish;
import vo.DishPageVO;

public interface DishService {
    //菜品的分页查询
    public DishPageVO dishPageService(DishPageDTO dishPageDTO);
    //新增菜品
    public void addDishService(Dish dish);
}
