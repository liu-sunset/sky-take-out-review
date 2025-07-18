package sky.project.service;

import dto.EmployeeLoginDTO;
import vo.EmployeeLoginVO;

public interface EmployeeService {
    //员工登录
    public EmployeeLoginVO empLoginService(EmployeeLoginDTO employeeLoginDTO);
}
