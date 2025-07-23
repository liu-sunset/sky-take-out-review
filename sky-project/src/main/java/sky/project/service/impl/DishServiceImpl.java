package sky.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dto.DishPageDTO;
import entity.Dish;
import entity.DishFlavor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sky.project.mapper.DishFlavorMapper;
import sky.project.mapper.DishMapper;
import sky.project.service.DishService;
import vo.DishPageVO;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    //菜品的分页查询
    @Override
    public DishPageVO dishPageService(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(),dishPageDTO.getPageSize());
        Page<Dish> page = dishMapper.dishPageMapper(dishPageDTO);
        return DishPageVO.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    //新增菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDishService(Dish dish) {
        //新增菜品基本信息
        dishMapper.addDishMapper(dish);
        List<DishFlavor> dishFlavorList = dish.getFlavors();
        Long dishId = dish.getId();
        for (DishFlavor dishFlavor : dishFlavorList){
            dishFlavor.setDishId(dishId);
        }
        //新增菜品口味信息
        dishFlavorMapper.addDishFlavorMapper(dishFlavorList);
    }
}
