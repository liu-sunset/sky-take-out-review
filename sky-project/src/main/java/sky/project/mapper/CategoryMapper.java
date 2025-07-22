package sky.project.mapper;

import annotation.AutoFillAspect;
import com.github.pagehelper.Page;
import dto.CatePageDTO;
import entity.Category;
import enums.OperStat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    //新增分类
    @AutoFillAspect(OperStat.INSERT)
    public void addCategoryMapper(Category category);
    //分类的分页查询
    public Page<Category> catePageMapper(CatePageDTO catePageDTO);
    //修改分类状态
    @AutoFillAspect(OperStat.UPDATE)
    public void modifyCateMapper(Category category);
    //删除分类
    public void deleteCateMapper(long id);
}
