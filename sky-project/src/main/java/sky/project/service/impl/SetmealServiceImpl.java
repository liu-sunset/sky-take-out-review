package sky.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dto.AddSetmealDTO;
import dto.SetmealPageDTO;
import entity.Setmeal;
import entity.SetmealDish;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sky.project.mapper.CategoryMapper;
import sky.project.mapper.SetmealDishMapper;
import sky.project.mapper.SetmealMapper;
import sky.project.service.SetmealService;
import vo.SetmealPageVO;
import vo.SetmealVO;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    //新增套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSetmealService(AddSetmealDTO addSetmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(addSetmealDTO,setmeal);
        //新增套餐基本信息
        setmealMapper.addSetmealMapper(setmeal);

        for (SetmealDish setmealDish : setmeal.getSetmealDishes()){
            setmealDish.setSetmealId(setmeal.getId());
        }
        //新增套餐餐品信息
        setmealDishMapper.addSetmealDishMapper(setmeal.getSetmealDishes());
    }

    //套餐分页查询
    @Override
    public SetmealPageVO setmealPageService(SetmealPageDTO setmealPageDTO) {
        PageHelper.startPage(setmealPageDTO.getPage(),setmealPageDTO.getPageSize());
        Page<Setmeal> page = setmealMapper.setmealPageMapper(setmealPageDTO);
        List<Setmeal> pageResult = page.getResult();
        List<SetmealVO> setmealVOList = new ArrayList<>();
        if (pageResult!=null&&!pageResult.isEmpty()){
            for(Setmeal setmeal : pageResult){
                SetmealVO setmealVO = new SetmealVO();
                BeanUtils.copyProperties(setmeal,setmealVO);
                setmealVOList.add(setmealVO);
            }
            if(pageResult!=null&&!pageResult.isEmpty()){
                String categoryName = categoryMapper.selectNameByIdMapper(pageResult.get(0).getCategoryId());
                for (SetmealVO setmealVO : setmealVOList){
                    setmealVO.setCategoryName(categoryName);
                }
            }
        }
      return SetmealPageVO.builder()
              .total(page.getTotal())
              .records(setmealVOList)
              .build();
    }

    //根据ID查询套餐
    @Override
    public SetmealVO selectByIdService(Long id) {
        SetmealVO setmealVO = new SetmealVO();
        //查询套餐基本信息
        Setmeal setmeal = setmealMapper.selectByIdMapper(id);
        //查询套餐菜品信息
        List<SetmealDish> setmealDishList = setmealDishMapper.selectBySetmealIdMapper(id);
        BeanUtils.copyProperties(setmeal,setmealVO);
        setmealVO.setSetmealDishes(setmealDishList);
        String categoryName = categoryMapper.selectNameByIdMapper(setmeal.getCategoryId());
        setmealVO.setCategoryName(categoryName);
        return setmealVO;
    }

    //修改套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifySetmealService(Setmeal setmeal) {
        //修改套餐基本信息
        setmealMapper.modifySetmealMapper(setmeal);
        //删除套餐的所有菜品
        setmealDishMapper.deleteSetmealDishMapper(setmeal.getId());
        //添加套餐的菜品
        setmealDishMapper.addSetmealDishMapper(setmeal.getSetmealDishes());
    }

    //修改套餐状态
    @Override
    public void modifySetmealStatusService(Long id, Integer status) {
        Setmeal setmeal = new Setmeal();
        setmeal.setId(id);
        setmeal.setStatus(status);
        setmealMapper.modifySetmealMapper(setmeal);
    }

    //批量删除套餐
    @Override
    public void deleteSetmealService(List<Long> ids) {
        //删除套餐基本信息
        setmealMapper.deleteSetmealMapper(ids);
        //删除套餐菜品信息
        setmealDishMapper.deleteAllSetmealDishMapper(ids);
    }
}
