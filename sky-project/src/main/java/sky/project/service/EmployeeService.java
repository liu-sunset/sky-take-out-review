package sky.project.service;

import dto.EditPasswordDTO;
import dto.EmpDTO;
import dto.EmpPageDTO;
import dto.EmployeeLoginDTO;
import entity.Employee;
import vo.EmpPageVO;
import vo.EmployeeLoginVO;

import java.util.List;

public interface EmployeeService {
    //员工登录
    public EmployeeLoginVO empLoginService(EmployeeLoginDTO employeeLoginDTO);
    //新增员工
    public void addEmpService(EmpDTO empDTO);
    //员工分页查询
    public EmpPageVO empPageService(EmpPageDTO empPageDTO);
    //员工账号状态修改
    public void empStatusModifyService(Integer status,long id);
    //根据ID查询员工
    public Employee selectEmpByIdService(long id);
    //修改员工信息
    public void modifyEmpService(EmpDTO empDTO);
    //修改员工密码
    public void edieEmpPasswordService(EditPasswordDTO editPasswordDTO);
}
