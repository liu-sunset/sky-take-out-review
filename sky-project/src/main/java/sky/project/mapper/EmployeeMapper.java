package sky.project.mapper;

import com.github.pagehelper.Page;
import dto.EmpPageDTO;
import entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    //根据条件查询员工
    public List<Employee> selectEmpMapper(Employee employee);
    //新增员工
    public void addEmpMapper(Employee employee);
    //员工分页查询
    public Page<Employee> empPageMapper(EmpPageDTO empPageDTO);
    //修改员工信息
    public void empModifyMapper(Employee employee);
}
