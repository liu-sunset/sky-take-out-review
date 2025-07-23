package sky.project.service;

import dto.CatePageDTO;
import dto.CategoryDTO;
import entity.Category;
import vo.CatePageVO;

import java.util.List;

public interface CategoryService {
    //新增分类
    public void addCategoryService(CategoryDTO categoryDTO);
    //分类的分页查询
    public CatePageVO catePageService(CatePageDTO catePageDTO);
    //修改分类的状态
    public void modifyCateStatusService(Long id,Integer status);
    //修改分类
    public void modifyCateService(CategoryDTO categoryDTO);
    //删除分类
    public void deleteCateService(Long id);
    //根据type查找分类
    public List<Category> selectByTypeService(Integer type);
}
