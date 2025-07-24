package sky.project.service;

import dto.AddSetmealDTO;
import dto.SetmealPageDTO;
import entity.Setmeal;
import entity.SetmealDish;
import org.aspectj.weaver.ast.Literal;
import vo.SetmealPageVO;
import vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    //新增套餐
    public void addSetmealService(AddSetmealDTO addSetmealDTO);
    //套餐的分页查询
    public SetmealPageVO setmealPageService(SetmealPageDTO setmealPageDTO);
    //根据ID查询套餐
    public SetmealVO selectByIdService(Long id);
    //修改套餐
    public void modifySetmealService(Setmeal setmeal);
    //修改套餐状态
    public void modifySetmealStatusService(Long id,Integer status);
    //批量删除套餐基本信息
    public void deleteSetmealService(List<Long> ids);
}
