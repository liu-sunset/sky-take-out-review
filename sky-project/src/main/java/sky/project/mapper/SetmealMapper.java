package sky.project.mapper;

import annotation.AutoFillAspect;
import com.github.pagehelper.Page;
import dto.SetmealPageDTO;
import entity.Setmeal;
import entity.SetmealDish;
import enums.OperStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {
    //新增套餐基本信息
    @AutoFillAspect(OperStat.INSERT)
    public void addSetmealMapper(Setmeal setmeal);
    //套餐的分页查询
    public Page<Setmeal> setmealPageMapper(SetmealPageDTO setmealPageDTO);
    //根据ID查询套餐基本信息
    public Setmeal selectByIdMapper(Long id);
    //修改套餐基本信息
    @AutoFillAspect(OperStat.UPDATE)
    public void modifySetmealMapper(Setmeal setmeal);
    //删除套餐基本信息
    public void deleteSetmealMapper(List<Long> ids);
}
