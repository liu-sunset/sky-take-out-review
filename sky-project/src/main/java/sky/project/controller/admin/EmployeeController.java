package sky.project.controller.admin;

import dto.EmpDTO;
import dto.EmpPageDTO;
import dto.EmployeeLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.EmployeeService;
import vo.EmpPageVO;
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

    @PostMapping
    public Result addEmpController(@RequestBody EmpDTO empDTO){
        log.info("新增员工：{}",empDTO);
        employeeService.addEmpService(empDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result empPageController(EmpPageDTO empPageDTO){
        log.info("员工分页查询:{}",empPageDTO);
        EmpPageVO empPageVO = employeeService.empPageService(empPageDTO);
        return Result.success(empPageVO);
    }
}
