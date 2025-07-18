package sky.project.service.impl;

import constant.EmpConstant;
import dto.EmployeeLoginDTO;
import entity.Employee;
import exception.EmpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import property.JWTProperty;
import sky.project.mapper.EmployeeMapper;
import sky.project.service.EmployeeService;
import utils.JWTUtils;
import vo.EmployeeLoginVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
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
        map.put("empId",employeeList.get(0).getId());
        String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), map);
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .token(token)
                .id(employeeList.get(0).getId())
                .name(employeeList.get(0).getName())
                .username(employeeList.get(0).getUsername())
                .build();
        return employeeLoginVO;
    }
}
