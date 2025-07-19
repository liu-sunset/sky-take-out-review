package sky.project.mapper;

import entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    //根据条件查询员工
    public List<Employee> selectEmpMapper(Employee employee);
    //新增员工
    public void addEmpMapper(Employee employee);
}
