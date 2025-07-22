package sky.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import dto.CatePageDTO;
import dto.CategoryDTO;
import entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.project.mapper.CategoryMapper;
import sky.project.service.CategoryService;
import vo.CatePageVO;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    //新增分类
    @Override
    public void addCategoryService(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.addCategoryMapper(category);
    }

    //分类的分页查询
    @Override
    public CatePageVO catePageService(CatePageDTO catePageDTO) {
        PageHelper.startPage(catePageDTO.getPage(), catePageDTO.getPageSize());
        Page<Category> page = categoryMapper.catePageMapper(catePageDTO);
        return CatePageVO.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    //修改分类状态
    @Override
    public void modifyCateStatusService(Long id, Integer status) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .build();
        categoryMapper.modifyCateMapper(category);
    }

    //修改分类
    @Override
    public void modifyCateService(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.modifyCateMapper(category);
    }

    //删除分类
    @Override
    public void deleteCateService(Long id) {
        categoryMapper.deleteCateMapper(id);
    }
}
