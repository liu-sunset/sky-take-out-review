package sky.project.service.impl;

import ch.qos.logback.classic.Logger;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dto.DishPageDTO;
import entity.Dish;
import entity.DishFlavor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import result.Result;
import sky.project.mapper.CategoryMapper;
import sky.project.mapper.DishFlavorMapper;
import sky.project.mapper.DishMapper;
import sky.project.service.CategoryService;
import sky.project.service.DishService;
import vo.DishPageVO;
import vo.ModifyDishVO;

import java.util.List;


@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private CategoryMapper categoryMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyDishService(Dish dish) {
        dishMapper.modifyDishMapper(dish);
        //删除dish的所有口味信息
        dishFlavorMapper.delectByDishIdMapper(dish.getId());
        List<DishFlavor> flavorList = dish.getFlavors();
        if(flavorList!=null&&!flavorList.isEmpty()){
            for (DishFlavor dishFlavor : flavorList){
                dishFlavor.setDishId(dish.getId());
            }
            //重新添加口味信息
            dishFlavorMapper.addDishFlavorMapper(dish.getFlavors());
        }
    }

    //查询回显
    @Override
    public ModifyDishVO selectDishByIdService(long id) {
        ModifyDishVO modifyDishVO = new ModifyDishVO();
        Dish dish = dishMapper.selectDishByIdService(id);
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishIdMapper(dish.getId());
        BeanUtils.copyProperties(dish,modifyDishVO);
        String categoryName = categoryMapper.selectNameByIdMapper(dish.getCategoryId());
        modifyDishVO.setFlavors(dishFlavors);
        modifyDishVO.setCategoryName(categoryName);
        return modifyDishVO;
    }

    //批量删除菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDishService(List<Long> ids) {
        dishMapper.deleteDishMapper(ids);
        dishFlavorMapper.deleteDishFlavorMapper(ids);
    }

    //根据分类ID查询菜品
    @Override
    public List<Dish> selectByCategoryIdService(Long categoryId) {
        return dishMapper.selectByCategoryId(categoryId);
    }

    //修改菜品状态
    @Override
    public void modifyDishStatusService(Long id, Integer status) {
        dishMapper.modifyDishStatusMapper(id,status);
    }
}
