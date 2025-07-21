package sky.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import constant.EmpConstant;
import dto.EditPasswordDTO;
import dto.EmpDTO;
import dto.EmpPageDTO;
import dto.EmployeeLoginDTO;
import entity.Employee;
import exception.EmpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import property.JWTProperty;
import result.Result;
import sky.project.mapper.EmployeeMapper;
import sky.project.service.EmployeeService;
import utils.BaseContext;
import utils.JWTUtils;
import vo.EmpPageVO;
import vo.EmployeeLoginVO;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private JWTProperty jwtProperty;
    //员工登录
    @Override
    public EmployeeLoginVO empLoginService(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        Employee employee = new Employee();
        employee.setUsername(username);
        List<Employee> employeeList = employeeMapper.selectEmpMapper(employee);

        //如果没有此用户
        if(employeeList.isEmpty()){
            throw new EmpException(EmpConstant.EMP_NOT_EXSIT);
        }

        //密码md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(employeeList.get(0).getPassword())){
            throw new EmpException(EmpConstant.EMP_PASSWORD_ERROR);
        }

        if (Objects.equals(employeeList.get(0).getStatus(), EmpConstant.EMP_CLOSE)){
            throw new EmpException(EmpConstant.EMP_IS_CLOSE);
        }
        //构建token
        Map<String, Object> map = new HashMap<>();
        map.put(EmpConstant.EMP_ID_INTERCEPTER,employeeList.get(0).getId());
        String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), map);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .token(token)
                .id(employeeList.get(0).getId())
                .name(employeeList.get(0).getName())
                .username(employeeList.get(0).getUsername())
                .build();
        return employeeLoginVO;
    }

    //新增员工
    @Override
    public void addEmpService(EmpDTO empDTO) {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8));
        Employee employee = Employee.builder()
                .password(password)
                .build();
        BeanUtils.copyProperties(empDTO,employee);
        employeeMapper.addEmpMapper(employee);
    }

    //员工分页查询
    @Override
    public EmpPageVO empPageService(EmpPageDTO empPageDTO) {
        //开始分页查询
        PageHelper.startPage(empPageDTO.getPage(), empPageDTO.getPageSize());
        Page<Employee> page = employeeMapper.empPageMapper(empPageDTO);
        return EmpPageVO.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    //员工账号状态修改
    @Override
    public void empStatusModifyService(Integer status, long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.empModifyMapper(employee);
    }

    //根据ID查询员工
    @Override
    public Employee selectEmpByIdService(long id) {
        Employee employee = Employee.builder()
                .id(id)
                .build();
        return employeeMapper.selectEmpMapper(employee).get(0);
    }

    //修改员工信息
    @Override
    public void modifyEmpService(EmpDTO empDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(empDTO,employee);
        employeeMapper.empModifyMapper(employee);
    }

    //修改员工密码
    @Override
    public void edieEmpPasswordService(EditPasswordDTO editPasswordDTO) {
        String md5OldPassword = DigestUtils.md5DigestAsHex(editPasswordDTO.getOldPassword().getBytes(StandardCharsets.UTF_8));
        String md5NewPassword = DigestUtils.md5DigestAsHex(editPasswordDTO.getNewPassword().getBytes(StandardCharsets.UTF_8));
        editPasswordDTO.setOldPassword(md5OldPassword);
        editPasswordDTO.setNewPassword(md5NewPassword);
        Employee employee = Employee.builder()
                .id(editPasswordDTO.getEmpId())
                .password(editPasswordDTO.getOldPassword())
                .build();
        List<Employee> employeeList = employeeMapper.selectEmpMapper(employee);
        if(employeeList.isEmpty()){
            throw new EmpException(EmpConstant.EMP_PASSWORD_ERROR);
        }
        employee.setPassword(editPasswordDTO.getNewPassword());
        employeeMapper.empModifyMapper(employee);
    }

}
