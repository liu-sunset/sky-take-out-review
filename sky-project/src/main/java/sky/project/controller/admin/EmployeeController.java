package sky.project.controller.admin;

import dto.EmployeeLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.EmployeeService;
import vo.EmployeeLoginVO;


@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result empLoginController(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录信息:{}",employeeLoginDTO);
        EmployeeLoginVO employeeLoginVO = employeeService.empLoginService(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }
}
