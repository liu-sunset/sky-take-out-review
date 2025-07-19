package sky.project.service;

import dto.EmpDTO;
import dto.EmployeeLoginDTO;
import vo.EmployeeLoginVO;

public interface EmployeeService {
    //员工登录
    public EmployeeLoginVO empLoginService(EmployeeLoginDTO employeeLoginDTO);
    //新增员工
    public void addEmpService(EmpDTO empDTO);
}
